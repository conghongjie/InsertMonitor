package com.elvis.android.insert_monitor.data;

import android.os.Handler;
import android.os.HandlerThread;

import com.elvis.android.insert_monitor.InsertMonitor;
import com.elvis.android.insert_monitor.obj.AbsInfo;
import com.elvis.android.insert_monitor.ui.UIAnalysisWrapper;

/**
 * Created by conghongjie on 2018/6/25.
 */

public class InsertMonitorDataHandler {


    /**
     * 一个时间段只能保留一个实例对象
     */
    private static InsertMonitorDataHandler nowDataHandler;

    public static void setNowDataHandler(InsertMonitorDataHandler dataHandler) {
        nowDataHandler = dataHandler;
    }

    public static InsertMonitorDataHandler getNowDataHandler() {
        return nowDataHandler;
    }




    /**
     * 数据处理线程
     */
    private static HandlerThread handlerThread = null;
    private static Handler handler = null;
    private static Handler getHandler(){
        if(handler==null){
            synchronized (InsertMonitorDataHandler.class){
                if(handler==null) {
                    handlerThread = new HandlerThread("MonitorHandlerThread");
                    handlerThread.start();
                    handler = new Handler(handlerThread.getLooper());
                }
            }
        }
        return handler;
    }



    public void handleAIDLData(final String infoString, final boolean isUpload){
        getHandler().post(new Runnable() {
            @Override
            public void run() {
                //UI模块：
                UIAnalysisWrapper wrapper = UIAnalysisWrapper.getNowAnalysisWrapper();
                if (wrapper!=null && wrapper.isImplEnable()){
                    wrapper.onData(infoString);
                }
                //数据存储：
                DataSaver.save(infoString);
                //数据上传：
                if (isUpload){
                    DataUploader.upload(infoString);
                }
            }
        });
    }

    public void handleSelfData(final AbsInfo info, final boolean isUpload){
        getHandler().post(new Runnable() {
            @Override
            public void run() {
                String infoString = InsertMonitor.getIJson().toJson(info);
                //UI模块：
                UIAnalysisWrapper wrapper = UIAnalysisWrapper.getNowAnalysisWrapper();
                if (wrapper!=null && wrapper.isImplEnable()){
                    wrapper.onData(infoString);
                }
                //数据存储：
                DataSaver.save(infoString);
                //数据上传：
                if (isUpload){
                    DataUploader.upload(infoString);
                }
            }
        });

    }






}
