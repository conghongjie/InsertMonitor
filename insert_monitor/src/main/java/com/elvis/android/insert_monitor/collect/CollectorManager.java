package com.elvis.android.insert_monitor.collect;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.elvis.android.insert_monitor.aidl.InsertMonitorAIDL;
import com.elvis.android.insert_monitor.collect.aspectjx.IOCollector;
import com.elvis.android.insert_monitor.collect.aspectjx.InflateCollector;
import com.elvis.android.insert_monitor.collect.hack.ActivityCollector;
import com.elvis.android.insert_monitor.collect.normal.BaseCollector;
import com.elvis.android.insert_monitor.collect.normal.FrameCollector;
import com.elvis.android.insert_monitor.data.InsertMonitorDataHandler;
import com.elvis.android.insert_monitor.obj.AbsInfo;
import com.elvis.android.insert_monitor.ui.UIAnalysisWrapper;
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
        //检测环境
        if (!ProcessUtils.isMainProcess(context)){
            return false;
        }
        if (!checkMonitorEnvironmentEnable()){
            return false;
        }
        //初始化参数
        int targetPid = android.os.Process.myPid();
        Log.e("ElvisKK_1","targetPid:"+targetPid);
        ISender aidlSender = new ISender() {
            @Override
            public void send(AbsInfo info, boolean isUpload) {
                InsertMonitorAIDL.sendByAIDL(info,isUpload);
            }
        };
        //开启monitor进程采集
        InsertMonitorAIDL.startMonitorProcess(context);
        //开启主（本）进程采集
        IOCollector.start(aidlSender);
        InflateCollector.start(aidlSender,context);
        ActivityCollector.start(aidlSender);
        FrameCollector.start(aidlSender,application);
        return true;
    }


    /**
     * 监控进程采集
     */
    public static boolean startInMonitorProcess(Application application, Context context){
        //检测环境
        if (!ProcessUtils.isInsertMonitorProcess(context)){
            return false;
        }
        if (!checkMonitorEnvironmentEnable()){
            return false;
        }
        //参数
        int targetPid = ProcessUtils.getMainProcessPid(context);
        Log.e("ElvisKK_2","targetPid:"+targetPid);
        ISender iSender = new ISender() {
            @Override
            public void send(AbsInfo info, boolean isUpload) {
                InsertMonitorDataHandler.handleSelfData(info,isUpload);
            }
        };
        //关闭
        BaseCollector.stop();
        //数据清除
        UIAnalysisWrapper.getInstance().clearData();
        //采集
        BaseCollector.start(iSender,context,targetPid);
        return true;
    }


    private static boolean checkMonitorEnvironmentEnable(){
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt<16){//FrameCollector要求16版本以上
            return false;
        }
        return true;
    }











}
