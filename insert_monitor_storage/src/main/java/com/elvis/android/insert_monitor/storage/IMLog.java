package com.elvis.android.insert_monitor.storage;

/**
 * 日志
 * 1、保存日志
 * 2、回捞日志
 *
 * Created by conghongjie on 2018/6/20.
 */

public class IMLog {

    public static String gtrDirPath = "/sdcard/InsertMonitor/";

    public static String packageName;

    public static long appStartTime;

    public static void init(String packageName,long appStartTime){
        IMLog.packageName = packageName;
        IMLog.appStartTime = appStartTime;
    }



}
