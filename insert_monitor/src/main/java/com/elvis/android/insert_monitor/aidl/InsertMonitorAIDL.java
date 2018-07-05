package com.elvis.android.insert_monitor.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.elvis.android.insert_monitor.InsertMonitor;
import com.elvis.android.insert_monitor.obj.AbsInfo;

import java.util.ArrayList;

/**
 * Created by conghongjie on 2018/6/25.
 */




public class InsertMonitorAIDL {


    private static IInsertMonitorAIDL iInsertMonitorAIDL;
    private static ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iInsertMonitorAIDL = IInsertMonitorAIDL.Stub.asInterface(service);
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            iInsertMonitorAIDL = null;
        }
    };


    /**
     * 启动监控进程
     * @param context
     */
    public static void startMonitorProcess(Context context) {
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        intent.setAction("com.elvis.android.InsertMonitorService");
        context.bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }



    private static ArrayList<AbsInfo> infoQueue = new ArrayList<>();
    private static ArrayList<Boolean> uploadQueue = new ArrayList<>();

    /**
     * 数据发送
     * @param info
     * @param isUpload
     */
    public static void sendByAIDL(AbsInfo info,boolean isUpload){
        infoQueue.add(info);
        uploadQueue.add(isUpload);
        if (iInsertMonitorAIDL==null || InsertMonitor.getIJson()==null){
            while (infoQueue.size() > 2000) {
                infoQueue.remove(0);
                uploadQueue.remove(0);
            }
        }else{
            while (infoQueue.size() > 0) {
                AbsInfo sendInfo = infoQueue.remove(0);
                Boolean sendIsUpload = uploadQueue.remove(0);
                try {
                    iInsertMonitorAIDL.send(InsertMonitor.getIJson().toJson(sendInfo),sendIsUpload);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }







}
