package com.elvis.android.insert_monitor.collect.frame;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.Choreographer;

import com.elvis.android.insert_monitor.collect.AbsCollector;
import com.elvis.android.insert_monitor.obj.info.FrameInfo;
import com.elvis.android.insert_monitor.obj.info.StackInfo;

/**
 *
 * Created by conghongjie on 2018/6/24.
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class FrameCollector extends AbsCollector {


    Application application;

    public FrameCollector(ISender sender, Application application) {
        super(sender);
        this.application = application;
    }


    private boolean isRun = false;

    @Override
    public boolean start(){
        if (!isRun){
            initHandler();
            if(handler!=null) {
                Choreographer.getInstance().postFrameCallback(frameCallback);// 监控frame
                handler.postDelayed(stackCollectRunnable, interval);// 栈采集任务
                application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);// Activity回退栈监听器
                isRun = true;
            }
        }
        return isRun;
    }

    @Override
    public boolean stop() {
        if(handler!=null){
            application.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);// Activity回退栈监听器
            handler.removeCallbacks(stackCollectRunnable);// 栈采集任务
            Choreographer.getInstance().removeFrameCallback(frameCallback);// 监控frame
        }
        isRun = false;
        return true;
    }


    /**
     * 采集线程
     */
    private HandlerThread handlerThread = null;
    private Handler handler = null;
    private void initHandler(){
        if(handler==null){
            handlerThread = new HandlerThread("GTRChoreographerMonitorThread");
            handlerThread.start();
            handler = new Handler(handlerThread.getLooper());
        }
    }

    /**
     * 栈采集任务（当应用处于后台时，暂停采集）
     */
    public int handlerThreadId = -1;
    private Thread uiThread = Looper.getMainLooper().getThread();
    private int interval = 30;//采集间隔--30ms
    private Runnable stackCollectRunnable = new Runnable() {
        @Override
        public void run() {
            if(handlerThreadId==-1){
                handlerThreadId = android.os.Process.myTid();
            }
            StringBuilder stackStringBuilder = new StringBuilder();
            for (StackTraceElement stackTraceElement : uiThread.getStackTrace()) {
                stackStringBuilder.append(stackTraceElement.toString()).append("&&rn&&");
            }
            long time = System.currentTimeMillis();
            //封装发送数据：
            String stack = stackStringBuilder.toString();
            StackInfo stackInfo = new StackInfo(time);
            stackInfo.time = time;
            stackInfo.stack = stack;
            sender.send(stackInfo,false);
            handler.postDelayed(stackCollectRunnable, interval);
        }
    };



    /**
     * Frame采集任务（当应用处于后台时，暂停采集）
     */
    private Choreographer.FrameCallback frameCallback = new Choreographer.FrameCallback() {//系统绘帧回调
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public void doFrame(long frameTimeNanos) {
            long thisTime = System.currentTimeMillis();
            //关闭栈采集
            handler.removeCallbacks(stackCollectRunnable);
            long time = System.currentTimeMillis();
            //封装发送数据：
            FrameInfo frameInfo = new FrameInfo(time);
            frameInfo.time = time;
            sender.send(frameInfo,false);
            //开启下一个doFrame监控
            Choreographer.getInstance().postFrameCallback(frameCallback);
            //重启栈采集
            handler.postDelayed(stackCollectRunnable,interval);
        }
    };


    /**
     * 前后台监听控制器：（当应用处于后台时，暂停采集）
     */
    Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
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
                    handler.removeCallbacks(stackCollectRunnable);
                    Choreographer.getInstance().removeFrameCallback(frameCallback);
                    //start
                    Choreographer.getInstance().postFrameCallback(frameCallback);
                    handler.postDelayed(stackCollectRunnable, interval);
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

    private Runnable stopDelayRunnable = new Runnable() {
        @Override
        public void run() {
            //stop
            if(handler!=null){
                handler.removeCallbacks(stackCollectRunnable);
                Choreographer.getInstance().removeFrameCallback(frameCallback);
            }
        }
    };






}
