package com.elvis.android.insert_monitor.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.elvis.android.insert_monitor.collect.CollectorManager;
import com.elvis.android.insert_monitor.data.InsertMonitorDataHandler;
import com.elvis.android.insert_monitor.ui.UIAnalysisWrapper;

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
        CollectorManager.startInMonitorProcess(getApplication(),getApplicationContext());
        return new AIDLDataReceiver();
    }

    public static class AIDLDataReceiver extends IInsertMonitorAIDL.Stub{

        public AIDLDataReceiver(){
            //保证数据写入新文件
            InsertMonitorDataHandler nowDataHandler = new InsertMonitorDataHandler();
            InsertMonitorDataHandler.setNowDataHandler(nowDataHandler);
            //保证数据发送给新的分析器
            UIAnalysisWrapper nowAnalysisWrapper = new UIAnalysisWrapper();
            UIAnalysisWrapper.setNowAnalysisWrapper(nowAnalysisWrapper);
            nowAnalysisWrapper.tryRegister();
        }

        @Override
        public void send(String info, boolean isUpload) throws RemoteException {
            InsertMonitorDataHandler nowDataHandler = InsertMonitorDataHandler.getNowDataHandler();
            if (nowDataHandler!=null){
                nowDataHandler.handleAIDLData(info,isUpload);
            }
        }
    }
}
