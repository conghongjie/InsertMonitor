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
        return CollectorManager.startInMainProcess(application,context);
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
