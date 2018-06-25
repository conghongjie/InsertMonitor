package com.elvis.android.insert_monitor.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by conghongjie on 2018/6/25.
 */

public class ProcessUtils {



    public final static String INSERT_MONITOR_PROCESS_SUFFIX = ":insert_monitor";
    public static boolean isInsertMonitorProcess(Context context) {
        String processName = getCurProcessName(context);
        if (processName != null && processName.endsWith(INSERT_MONITOR_PROCESS_SUFFIX)) {
            return true;
        }
        return false;
    }

    public static boolean isMainProcess(Context context) {
        String processName = getCurProcessName(context);
        if (processName != null && processName.contains(":")) {
            return false;
        }
        return (processName != null && processName.equals(context.getPackageName()));
    }




    private static String sCurProcessName = null;
    public static String getCurProcessName(Context context) {
        String procName = sCurProcessName;
        if (procName!=null && !procName.equals("")) {
            return procName;
        }
        try{
            int pid = android.os.Process.myPid();
            ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
                if (appProcess.pid == pid) {
                    sCurProcessName = appProcess.processName;
                    return sCurProcessName;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        sCurProcessName = getCurProcessNameFromProc();
        return sCurProcessName;
    }

    private static String getCurProcessNameFromProc() {
        BufferedReader cmdlineReader = null;
        try {
            cmdlineReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + android.os.Process.myPid() + "/cmdline"), "iso-8859-1"));
            int c;
            StringBuilder processName = new StringBuilder();
            while ((c = cmdlineReader.read()) > 0) {
                processName.append((char) c);
            }
            return processName.toString();
        } catch (Throwable e) {
        } finally {
            if (cmdlineReader != null) {
                try {
                    cmdlineReader.close();
                } catch (Exception e) {
                }
            }
        }
        return null;
    }


    public static int getMainProcessPid(Context context) {
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> mRunningProcess = am.getRunningAppProcesses();
        int pid=-1;
        for (ActivityManager.RunningAppProcessInfo amProcess : mRunningProcess){
            if(amProcess.processName.equals(context.getPackageName())){
                pid=amProcess.pid;
                break;
            }
        }
        return pid;

    }
}
