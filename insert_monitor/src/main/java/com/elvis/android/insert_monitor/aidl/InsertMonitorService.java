package com.elvis.android.insert_monitor.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.elvis.android.insert_monitor.collect.CollectorManager;
import com.elvis.android.insert_monitor.data.InsertMonitorDataHandler;

/**
 * Created by conghongjie on 2018/6/25.
 */

public class InsertMonitorService extends Service{

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("ElvisKK_2","====:");
        CollectorManager.startInMonitorProcess(getApplication(),getApplicationContext());
        return new AIDLDataReceiver();
    }

    public static class AIDLDataReceiver extends IInsertMonitorAIDL.Stub{
        @Override
        public void send(String info, boolean isUpload) throws RemoteException {
            InsertMonitorDataHandler.handleAIDLData(info,isUpload);
        }
    }
}
