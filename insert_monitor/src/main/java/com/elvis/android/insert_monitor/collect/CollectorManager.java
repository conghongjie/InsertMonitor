package com.elvis.android.insert_monitor.collect;

import android.app.Application;
import android.content.Context;
import com.elvis.android.insert_monitor.aidl.InsertMonitorAIDL;
import com.elvis.android.insert_monitor.collect.aspectjx.IOCollector;
import com.elvis.android.insert_monitor.collect.hack.ActivityCollector;
import com.elvis.android.insert_monitor.collect.normal.BaseCollector;
import com.elvis.android.insert_monitor.collect.normal.FrameCollector;
import com.elvis.android.insert_monitor.data.InsertMonitorDataHandler;
import com.elvis.android.insert_monitor.obj.AbsInfo;
import com.elvis.android.insert_monitor.utils.ProcessUtils;


/**
 *
 * 采集器分为两部分：
 * 1、在目标进程中执行
 * 2、在监控进程中执行
 *
 * Created by conghongjie on 2018/6/24.
 */

public class CollectorManager {



    /**
     * 主进程采集
     */
    public static boolean startInMainProcess(Application application, Context context){
        //参数
        int targetPid = android.os.Process.myPid();
        ISender aidlSender = new ISender() {
            @Override
            public void send(AbsInfo info, boolean isUpload) {
                InsertMonitorAIDL.sendByAIDL(info,isUpload);
            }
        };
        //采集
        IOCollector.start(aidlSender);
        ActivityCollector.start(aidlSender);
        FrameCollector.start(aidlSender,application);
        return true;
    }


    /**
     * 监控进程采集
     */
    public static boolean startInMonitorProcess(Application application, Context context){
        //参数
        int targetPid = ProcessUtils.getMainProcessPid(context);
        ISender iSender = new ISender() {
            @Override
            public void send(AbsInfo info, boolean isUpload) {
                InsertMonitorDataHandler.handleSelfData(info,isUpload);
            }
        };
        //采集
        BaseCollector.start(iSender,context,targetPid);
        return true;
    }













}
