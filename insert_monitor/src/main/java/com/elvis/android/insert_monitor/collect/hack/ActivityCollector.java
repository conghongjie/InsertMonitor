package com.elvis.android.insert_monitor.collect.hack;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.elvis.android.insert_monitor.collect.ISender;
import com.elvis.android.insert_monitor.obj.AbsInfo;
import com.elvis.android.insert_monitor.obj.info.ActivityInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by conghongjie on 2018/6/27.
 */

public class ActivityCollector{

    private static ISender sender;
    private static boolean isCollect = false;
    public static boolean start(ISender sender){
        ActivityCollector.sender = sender;
        //if not hack instrument, hack
        hackInstrumentation();
        //register hack Callback
        isCollect = true;
        return true;
    }

    public static boolean stop() {
        //unregister hack Callback
        isCollect = false;
        return true;
    }

    public static void sendInfo(AbsInfo info, boolean isUpload){
        try {
            sender.send(info,isUpload);
        }catch (Exception e){
        }
    }


    /**
     * hack
     */
    private static boolean hack = false;
    public static void hackInstrumentation() {
        if (hack){
            return;
        }
        try {
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Method sCurrentActivityThread = activityThread.getDeclaredMethod("currentActivityThread");
            sCurrentActivityThread.setAccessible(true);
            //获取ActivityThread 对象
            Object activityThreadObject = sCurrentActivityThread.invoke(activityThread);
            //获取 Instrumentation 对象
            Field mInstrumentation = activityThread.getDeclaredField("mInstrumentation");
            mInstrumentation.setAccessible(true);
            Instrumentation instrumentation = (Instrumentation) mInstrumentation.get(activityThreadObject);
            NewInstrumentation customInstrumentation = new NewInstrumentation(instrumentation);
            //将我们的 customInstrumentation 设置进去
            mInstrumentation.set(activityThreadObject, customInstrumentation);
            hack = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class NewInstrumentation extends Instrumentation {

        Instrumentation oldInstrumentation;
        public NewInstrumentation(Instrumentation oldInstrumentation){
            this.oldInstrumentation = oldInstrumentation;
        }

        @Override
        public void callActivityOnCreate(Activity activity, Bundle icicle) {
            if (isCollect){
                long start = System.currentTimeMillis();
                super.callActivityOnCreate(activity, icicle);
                long end = System.currentTimeMillis();
                ActivityInfo activityInfo = new ActivityInfo(start);
                activityInfo.className = activity==null?"":activity.getClass().getName();
                activityInfo.hashCode = activity==null?"":(""+activity.hashCode());
                activityInfo.methodName = ActivityInfo.Activity_onCreate;
                activityInfo.startTime = start;
                activityInfo.endTime = end;
                ActivityCollector.sendInfo(activityInfo,false);
            }else {
                super.callActivityOnCreate(activity, icicle);
            }
        }

        @Override
        public void callActivityOnCreate(Activity activity, Bundle icicle, PersistableBundle persistentState) {
            if (isCollect){
                long start = System.currentTimeMillis();
                super.callActivityOnCreate(activity, icicle, persistentState);
                long end = System.currentTimeMillis();
                ActivityInfo activityInfo = new ActivityInfo(start);
                activityInfo.className = activity==null?"":activity.getClass().getName();
                activityInfo.hashCode = activity==null?"":(""+activity.hashCode());
                activityInfo.methodName = ActivityInfo.Activity_onCreate_2;
                activityInfo.startTime = start;
                activityInfo.endTime = end;
                ActivityCollector.sendInfo(activityInfo,false);
            }else {
                super.callActivityOnCreate(activity, icicle, persistentState);
            }
        }

        @Override
        public void callActivityOnStart(Activity activity) {
            if (isCollect){
                long start = System.currentTimeMillis();
                super.callActivityOnStart(activity);
                long end = System.currentTimeMillis();
                ActivityInfo activityInfo = new ActivityInfo(start);
                activityInfo.className = activity==null?"":activity.getClass().getName();
                activityInfo.hashCode = activity==null?"":(""+activity.hashCode());
                activityInfo.methodName = ActivityInfo.Activity_onStart;
                activityInfo.startTime = start;
                activityInfo.endTime = end;
                ActivityCollector.sendInfo(activityInfo,false);
            }else {
                super.callActivityOnStart(activity);
            }
        }

        @Override
        public void callActivityOnResume(Activity activity) {
            if (isCollect){
                long start = System.currentTimeMillis();
                super.callActivityOnResume(activity);
                long end = System.currentTimeMillis();
                ActivityInfo activityInfo = new ActivityInfo(start);
                activityInfo.className = activity==null?"":activity.getClass().getName();
                activityInfo.hashCode = activity==null?"":(""+activity.hashCode());
                activityInfo.methodName = ActivityInfo.Activity_onResume;
                activityInfo.startTime = start;
                activityInfo.endTime = end;
                ActivityCollector.sendInfo(activityInfo,false);
            }else {
                super.callActivityOnResume(activity);
            }
        }

        @Override
        public void callActivityOnPause(Activity activity) {
            if (isCollect){
                long start = System.currentTimeMillis();
                super.callActivityOnPause(activity);
                long end = System.currentTimeMillis();
                ActivityInfo activityInfo = new ActivityInfo(start);
                activityInfo.className = activity==null?"":activity.getClass().getName();
                activityInfo.hashCode = activity==null?"":(""+activity.hashCode());
                activityInfo.methodName = ActivityInfo.Activity_onPause;
                activityInfo.startTime = start;
                activityInfo.endTime = end;
                ActivityCollector.sendInfo(activityInfo,false);
            }else {
                super.callActivityOnPause(activity);
            }
        }

        @Override
        public void callActivityOnStop(Activity activity) {
            if (isCollect){
                long start = System.currentTimeMillis();
                super.callActivityOnStop(activity);
                long end = System.currentTimeMillis();
                ActivityInfo activityInfo = new ActivityInfo(start);
                activityInfo.className = activity==null?"":activity.getClass().getName();
                activityInfo.hashCode = activity==null?"":(""+activity.hashCode());
                activityInfo.methodName = ActivityInfo.Activity_onStop;
                activityInfo.startTime = start;
                activityInfo.endTime = end;
                ActivityCollector.sendInfo(activityInfo,false);
            }else {
                super.callActivityOnStop(activity);
            }
        }

        @Override
        public void callActivityOnDestroy(Activity activity) {
            if (isCollect){
                long start = System.currentTimeMillis();
                super.callActivityOnDestroy(activity);
                long end = System.currentTimeMillis();
                ActivityInfo activityInfo = new ActivityInfo(start);
                activityInfo.className = activity==null?"":activity.getClass().getName();
                activityInfo.hashCode = activity==null?"":(""+activity.hashCode());
                activityInfo.methodName = ActivityInfo.Activity_onDestroy;
                activityInfo.startTime = start;
                activityInfo.endTime = end;
                ActivityCollector.sendInfo(activityInfo,false);
            }else {
                super.callActivityOnDestroy(activity);
            }
        }
    }
}
