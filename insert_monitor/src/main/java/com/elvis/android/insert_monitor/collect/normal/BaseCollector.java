package com.elvis.android.insert_monitor.collect.normal;

import android.content.Context;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.HandlerThread;

import com.elvis.android.insert_monitor.collect.ISender;
import com.elvis.android.insert_monitor.obj.info.BaseInfo;

/**
 *
 * Created by p_hongjcong on 2017/7/13.
 */

public class BaseCollector{


    private static final int COLLECT_INTERVAL = 1000;

    private static ISender sender;
    private static Context context;
    private static int targetPid = -1;

    private static boolean isRun = false;
    public static boolean start(ISender sender, Context context, int targetPid) {
        BaseCollector.sender = sender;
        BaseCollector.context = context;
        BaseCollector.targetPid = targetPid;
        if (!isRun){
            initHandler();
            if (handler!=null) {
                handler.postDelayed(baseCollectRunnable, 0);
                isRun = true;
            }
        }
        return isRun;
    }

    public static boolean stop() {
        if (handler!=null){
            handler.removeCallbacks(baseCollectRunnable);
        }
        isRun = false;
        return true;
    }



    /**
     * 采集线程
     */
    private static HandlerThread handlerThread = null;
    private static Handler handler = null;
    private static void initHandler(){
        if(handler==null){
            handlerThread = new HandlerThread("MonitorBaseThread");
            handlerThread.start();
            handler = new Handler(handlerThread.getLooper());
        }
    }

    /**
     * 采集任务
     */
    public static int handlerThreadId = -1;
    private static Runnable baseCollectRunnable = new Runnable() {
        @Override
        public void run() {
            if(handlerThreadId==-1){
                handlerThreadId = android.os.Process.myTid();
            }
            BaseInfo baseInfo = new BaseInfo(System.currentTimeMillis());
            baseInfo.processCpu = BaseCollectUtils.getCPU_app(targetPid);
            baseInfo.systemCpu = BaseCollectUtils.getCPU_total();
            baseInfo.threadCpus = BaseCollectUtils.getCPU_threads(targetPid);
            baseInfo.processMemory = BaseCollectUtils.getMemory_app(targetPid,context);
            baseInfo.flowUpload = TrafficStats.getUidTxBytes(targetPid);
            baseInfo.flowDownload = TrafficStats.getUidRxBytes(targetPid);
            sender.send(baseInfo,false);
            //下一个采集
            handler.postDelayed(baseCollectRunnable, COLLECT_INTERVAL);
        }
    };


}




