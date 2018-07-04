package com.elvis.android.insert_monitor.collect.normal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.util.Printer;
import android.view.Choreographer;

import com.elvis.android.insert_monitor.collect.ISender;
import com.elvis.android.insert_monitor.obj.info.BlockInfo;
import com.elvis.android.insert_monitor.obj.info.MessageInfo;
import com.elvis.android.insert_monitor.obj.info.SMInfo;
import com.elvis.android.insert_monitor.obj.info.StackInfo;
import com.elvis.android.insert_monitor.utils.StackUtils;

import java.util.ArrayList;

/**
 *
 * Created by conghongjie on 2018/6/24.
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class FrameCollector{


    private static ISender sender;
    private static Application application;

    private static boolean isRun = false;

    public static boolean start(ISender sender, Application application){
        FrameCollector.sender = sender;
        FrameCollector.application = application;
        if (!isRun){
            initHandler();
            if(handler!=null) {
                startFrame();
                application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);// Activity回退栈监听器
                isRun = true;
            }
        }
        return isRun;
    }

    public static boolean stop() {
        if(handler!=null){
            application.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);// Activity回退栈监听器
            stopFrame();
        }
        isRun = false;
        return true;
    }






    /**
     * Frame采集任务（当应用处于后台时，暂停采集）
     */
    static long nowSm = 0;
    static ArrayList<Long> framesInOneSecond = new ArrayList<>();//1s内所有frame的时间
    //sm值：
    static final long frameCollectSection = 200;                       //sm采集区间:200ms
    static long lastCollectSmTime = 0;
    //大卡顿：
    static final long bigBlockFrameLimit = 70;                         //大卡顿：Frame间隔限制
    static BlockInfo bigBlockInfo;
    //连续小卡顿：
    static final long serialBlockSMLimit = 40;                         //连续小卡顿：sm限制
    static BlockInfo serialBlockInfo;
    //控制
    static void startFrame(){
        long time = System.currentTimeMillis();
        //单次大卡顿:初始化
        if (bigBlockInfo==null){
            bigBlockInfo = new BlockInfo(time);
            bigBlockInfo.startTime = time;
        }else {
            bigBlockInfo.dataTime = time;
            bigBlockInfo.startTime = time;
            bigBlockInfo.endTime = 0;
            bigBlockInfo.frameNum =0;
            bigBlockInfo.messageInfos.clear();
            bigBlockInfo.stackInfos.clear();
        }
        Choreographer.getInstance().postFrameCallback(frameCallback);
        handler.postDelayed(stackCollectRunnable,interval);
        startLooperCollect();
    }
    static void stopFrame(){
        bigBlockInfo = null;
        serialBlockInfo = null;
        handler.removeCallbacks(stackCollectRunnable);
        Choreographer.getInstance().removeFrameCallback(frameCallback);
    }
    private static Choreographer.FrameCallback frameCallback = new Choreographer.FrameCallback() {//系统绘帧回调

        public void doFrame(long frameTimeNanos) {
            //关闭栈采集
            handler.removeCallbacks(stackCollectRunnable);
            //数据处理：
            long time = System.currentTimeMillis();
            framesInOneSecond.add(time);
            nowSm++;
            while (framesInOneSecond.size()>0 && framesInOneSecond.get(0)<time-1000){
                framesInOneSecond.remove(0);
                nowSm--;
            }
            //sm：
            if (time-lastCollectSmTime>frameCollectSection){
                lastCollectSmTime = time;
                SMInfo smInfo = new SMInfo(time);
                smInfo.time = time;
                smInfo.sm = nowSm;
                sender.send(smInfo,false);
            }
            //单次大卡顿:
            if (bigBlockInfo!=null && time-bigBlockInfo.startTime>bigBlockFrameLimit){
                bigBlockInfo.endTime = time;
                bigBlockInfo.frameNum = 1;
                sender.send(bigBlockInfo,false);
                bigBlockInfo = null;
            }
            //连续小卡顿：
            if (serialBlockInfo==null){
                if (nowSm<serialBlockSMLimit){
                    serialBlockInfo = new BlockInfo(time);
                    serialBlockInfo.startTime = time;
                    serialBlockInfo.frameNum = 1;
                }
            } else{
                if (nowSm<serialBlockSMLimit){
                    serialBlockInfo.frameNum++;
                }else {
                    serialBlockInfo.endTime = time;
                    sender.send(serialBlockInfo,false);
                    serialBlockInfo = null;
                }
            }
            //开启下一个监控
            startFrame();
        }
    };


    /**
     * 栈采集任务（当应用处于后台时，暂停采集）
     */
    private static HandlerThread handlerThread = null;
    private static Handler handler = null;
    private static void initHandler(){
        if(handler==null){
            handlerThread = new HandlerThread("GTRChoreographerMonitorThread");
            handlerThread.start();
            handler = new Handler(handlerThread.getLooper());
        }
    }

    private static int handlerThreadId = -1;
    private static Thread uiThread = Looper.getMainLooper().getThread();
    private static int interval = 30;//采集间隔--30ms
    private static Runnable stackCollectRunnable = new Runnable() {
        @Override
        public void run() {
            if(handlerThreadId==-1){
                handlerThreadId = android.os.Process.myTid();
            }
            //封装保存数据：
            long time = System.currentTimeMillis();
            String stack = StackUtils.getStack(uiThread);
            StackInfo stackInfo = new StackInfo(time);
            stackInfo.time = time;
            stackInfo.stack = stack;
            try {
                if (bigBlockInfo!=null){
                    bigBlockInfo.stackInfos.add(stackInfo);
                }
            }catch (Exception e){}
            try {
                if (serialBlockInfo!=null){
                    serialBlockInfo.stackInfos.add(stackInfo);
                }
            }catch (Exception e){}
            handler.postDelayed(stackCollectRunnable, interval);
        }
    };



    /**
     * Message监控
     */

    private static boolean isStart;
    private static long startTime;
    private static final Looper mainLooper = Looper.getMainLooper();
    private static void startLooperCollect(){
        isStart = true;//重置状态
        mainLooper.setMessageLogging(messageCollect);
    }
    private static Printer messageCollect = new Printer() {
        @Override
        public void println(String x) {
            if (!isStart){
                isStart = true;
                startTime = System.currentTimeMillis();
            }else {
                isStart = false;
                if (startTime !=0){
                    //封装保存数据：
                    long time = System.currentTimeMillis();
                    if (time-startTime>5){
                        MessageInfo messageInfo = new MessageInfo(startTime);
                        messageInfo.startTime = startTime;
                        messageInfo.endTime = time;
                        try {
                            if (bigBlockInfo!=null){
                                bigBlockInfo.messageInfos.add(messageInfo);
                            }
                        }catch (Exception e){}
                        try {
                            if (serialBlockInfo!=null){
                                serialBlockInfo.messageInfos.add(messageInfo);
                            }
                        }catch (Exception e){}
                    }
                }
            }
        }
    };


    /**
     * 前后台监听控制器：（当应用处于后台时，暂停采集）
     */
    static Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            if (isRun){
                if(handler!=null){
                    handler.removeCallbacks(stopDelayRunnable);
                    //stop
                    stopFrame();
                    //start
                    startFrame();
                }
            }
        }

        @Override
        public void onActivityPaused(Activity activity) {
            if (isRun){
                if(handler!=null){
                    handler.postDelayed(stopDelayRunnable,1500);
                }
            }
        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };

    private static Runnable stopDelayRunnable = new Runnable() {
        @Override
        public void run() {
            //stop
            if(handler!=null){
                stopFrame();
            }
        }
    };






}
