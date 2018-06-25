package com.elvis.android.insert_monitor.collect.base;

import android.content.Context;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.HandlerThread;

import com.elvis.android.insert_monitor.collect.AbsCollector;
import com.elvis.android.insert_monitor.obj.info.BaseInfo;

/**
 *
 * Created by p_hongjcong on 2017/7/13.
 */

public class BaseCollector extends AbsCollector{


    private final int COLLECT_INTERVAL = 3000;

    private Context context;
    private int targetPid = -1;

    public BaseCollector(ISender sender, Context context, int targetPid) {
        super(sender);
        this.context = context;
        this.targetPid = targetPid;
    }



    private boolean isRun = false;
    public boolean start() {
        if (!isRun){
            initHandler();
            if (handler!=null) {
                handler.postDelayed(baseCollectRunnable, 0);
                isRun = true;
            }
        }
        return isRun;
    }

    public boolean stop() {
        if (handler!=null){
            handler.removeCallbacks(baseCollectRunnable);
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
            handlerThread = new HandlerThread("GTRNormalMonitorThread");
            handlerThread.start();
            handler = new Handler(handlerThread.getLooper());
        }
    }

    /**
     * 采集任务
     */
    public int handlerThreadId = -1;
    private Runnable baseCollectRunnable = new Runnable() {
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




