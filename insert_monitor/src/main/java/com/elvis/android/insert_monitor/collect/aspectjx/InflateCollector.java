package com.elvis.android.insert_monitor.collect.aspectjx;

import android.content.Context;

import com.elvis.android.insert_monitor.collect.ISender;
import com.elvis.android.insert_monitor.obj.AbsInfo;

/**
 * Created by conghongjie on 2018/6/28.
 */

public class InflateCollector {

    public static final int MAX_INFLATE_TIME = 10;

    private static ISender sender;
    private static Context context;
    private static boolean isStart = false;

    public static void start(ISender sender, Context context){
        InflateCollector.sender = sender;
        InflateCollector.context =context;
        InflateCollector.isStart = true;
    }
    public static void stop(){
        InflateCollector.isStart = false;
        InflateCollector.context = null;
        sender = null;
    }

    public static boolean isStart() {
        return isStart;
    }

    public static Context getContext() {
        return context;
    }

    public static void sendInfo(AbsInfo info, boolean isUpload){
        try {
            if (isStart && sender!=null){
                sender.send(info,isUpload);
            }
        }catch (Exception e){
        }
    }
}
