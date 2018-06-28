package com.elvis.android.insert_monitor.collect.aspectjx;

import android.annotation.TargetApi;
import android.os.Build;

import com.elvis.android.insert_monitor.collect.ISender;
import com.elvis.android.insert_monitor.obj.AbsInfo;

/**
 *
 * Created by conghongjie on 2018/6/24.
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class IOCollector {

    private static ISender sender;
    public static void start(ISender sender){
        IOCollector.sender = sender;
    }
    public static void stop(){
        sender = null;
    }

    public static void sendInfo(AbsInfo info,boolean isUpload){
        try {
            sender.send(info,isUpload);
        }catch (Exception e){
        }
    }






}
