package com.elvis.android.insert_monitor.data;

import android.os.Handler;
import android.os.HandlerThread;

import com.elvis.android.insert_monitor.InsertMonitor;
import com.elvis.android.insert_monitor.obj.AbsInfo;
import com.elvis.android.insert_monitor.obj.info.ActivityInfo;
import com.elvis.android.insert_monitor.obj.info.BaseInfo;
import com.elvis.android.insert_monitor.obj.info.BlockInfo;
import com.elvis.android.insert_monitor.obj.info.DBInfo;
import com.elvis.android.insert_monitor.obj.info.InflateInfo;
import com.elvis.android.insert_monitor.obj.info.MessageInfo;
import com.elvis.android.insert_monitor.obj.info.SMInfo;
import com.elvis.android.insert_monitor.obj.info.StackInfo;
import com.elvis.android.insert_monitor.obj.info.ThreadCpuInfo;
import com.elvis.android.insert_monitor.ui.UIAnalysisWrapper;

import org.json.JSONObject;

/**
 * Created by conghongjie on 2018/6/25.
 */

public class InsertMonitorDataHandler {



    /**
     * 数据处理线程
     */
    private static HandlerThread handlerThread = null;
    private static Handler handler = null;
    private static Handler getHandler(){
        if(handler==null){
            synchronized (InsertMonitorDataHandler.class){
                if(handler==null) {
                    handlerThread = new HandlerThread("InsertMonitorDataHandler");
                    handlerThread.start();
                    handler = new Handler(handlerThread.getLooper());
                }
            }
        }
        return handler;
    }



    public static void handleAIDLData(final String infoString, final boolean isUpload){
        getHandler().post(new Runnable() {
            @Override
            public void run() {
                //UI模块：
                if (UIAnalysisWrapper.getInstance().isImplEnable()){
                    UIAnalysisWrapper.getInstance().onData(infoString);
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

    public static void handleSelfData(final AbsInfo info, final boolean isUpload){
        getHandler().post(new Runnable() {
            @Override
            public void run() {
                String infoString = InsertMonitor.getIJson().toJson(info);
                //UI模块：
                if (UIAnalysisWrapper.getInstance().isImplEnable()){
                    UIAnalysisWrapper.getInstance().onData(infoString);
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
