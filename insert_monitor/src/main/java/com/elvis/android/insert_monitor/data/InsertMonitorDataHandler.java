package com.elvis.android.insert_monitor.data;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

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
import com.elvis.android.insert_monitor.ui.InsertMonitorUIWrapper;

import org.json.JSONException;
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
                if (InsertMonitorUIWrapper.getInstance().isImplEnable()){
                    AbsInfo absInfo = transform(infoString);
                    InsertMonitorUIWrapper.getInstance().onData(absInfo);
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
                if (InsertMonitorUIWrapper.getInstance().isImplEnable()){
                    InsertMonitorUIWrapper.getInstance().onData(info);
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



    private static AbsInfo transform(String infoString){
        try {
            JSONObject jsonObject = new JSONObject(infoString);
            String dataType = jsonObject.getString("dataType");
            switch (dataType){
                case "ActivityInfo":
                    return InsertMonitor.getIJson().fromJson(infoString,ActivityInfo.class);
                case "BaseInfo":
                    return InsertMonitor.getIJson().fromJson(infoString,BaseInfo.class);
                case "BlockInfo":
                    return InsertMonitor.getIJson().fromJson(infoString,BlockInfo.class);
                case "DBInfo":
                    return InsertMonitor.getIJson().fromJson(infoString,DBInfo.class);
                case "InflateInfo":
                    return InsertMonitor.getIJson().fromJson(infoString,InflateInfo.class);
                case "MessageInfo":
                    return InsertMonitor.getIJson().fromJson(infoString,MessageInfo.class);
                case "SMInfo":
                    return InsertMonitor.getIJson().fromJson(infoString,SMInfo.class);
                case "StackInfo":
                    return InsertMonitor.getIJson().fromJson(infoString,StackInfo.class);
                case "ThreadCpuInfo":
                    return InsertMonitor.getIJson().fromJson(infoString,ThreadCpuInfo.class);
                default:
                    throw new Exception("未知类型数据："+(infoString==null?"null":infoString));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}
