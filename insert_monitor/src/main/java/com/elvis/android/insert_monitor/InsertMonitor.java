package com.elvis.android.insert_monitor;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.elvis.android.insert_monitor.aidl.InsertMonitorAIDL;
import com.elvis.android.insert_monitor.collect.CollectorManager;
import com.elvis.android.insert_monitor.utils.ProcessUtils;

/**
 * InsertMonitor
 *
 * 对外提供API
 *
 * Created by conghongjie on 2018/6/22.
 */

public class InsertMonitor {


    private static boolean isOffline = false;
    private static IJson iJson;
    private static Application application;
    private static Context context;




    public static void init(Application application, Context context, IJson iJson){
        if (ProcessUtils.isMainProcess(context) || ProcessUtils.isInsertMonitorProcess(context)) {
            InsertMonitor.application = application;
            InsertMonitor.context = context;
            InsertMonitor.iJson = iJson;
        }
    }

    public static boolean start(boolean isOffline){
        InsertMonitor.isOffline = isOffline;
        //检测环境
        if (!checkMonitorEnvironmentEnable()){
            return false;
        }
        //采集
        if (ProcessUtils.isMainProcess(context)){
            //启动Monitor进程
            InsertMonitorAIDL.startMonitorProcess(context);
            CollectorManager.startInMainProcess(application,context);
            return true;
        }else if (ProcessUtils.isInsertMonitorProcess(context)){
            CollectorManager.startInMonitorProcess(application,context);
            return true;
        }
        return false;
    }

    private static boolean checkMonitorEnvironmentEnable(){
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt<16){//FrameCollector要求16版本以上
            return false;
        }
        return true;
    }


    public static Context getContext() {
        return context;
    }

    public static Application getApplication() {
        return application;
    }

    public static IJson getIJson() {
        return iJson;
    }

    public static boolean isOffline() {
        return isOffline;
    }
}
