package com.elvis.android.insert_monitor.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;

import java.util.List;

/**
 * Created by conghongjie on 2018/3/15.
 */

public class ActivityUtils {




    /**
     * 判断是否有能够处理Intent的应用
     **/
    public static boolean hasAppHandleIntent(Context context, Intent intent) {
        if (null == context || null == intent) {
            return false;
        }
        try {
            return null != intent.resolveActivity(context.getPackageManager());
        } catch (Throwable e) {
        }
        return false;
    }

    /**
     * 尝试打开Activity
     * @param context
     * @param intent
     * @return
     */
    public static boolean tryStartActivity(Context context, Intent intent) {
        if (context==null || intent==null){
            return false;
        }
        try {
            if (!(context instanceof Activity)){
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 尝试打开应用主页面
     * @param context
     * @param packageName
     * @return
     */
    public static Intent getAppMainActivityIntent(Context context, String packageName){
        try {
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            resolveIntent.setPackage(packageName);
            List<ResolveInfo> resolveinfoList = context.getPackageManager().queryIntentActivities(resolveIntent, 0);
            ResolveInfo resolveinfo = resolveinfoList.iterator().next();
            if (resolveinfo != null) {
                String className = resolveinfo.activityInfo.name;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                ComponentName cn = new ComponentName(packageName, className);
                intent.setComponent(cn);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                return intent;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Intent是否有接受者
     * @param context
     * @param intent
     * @return
     */
    public static boolean isIntentAvaileble(Context context, Intent intent){
        try {
            List<ResolveInfo> resolves = context.getPackageManager().queryIntentActivities(intent,0);
            return resolves!=null && resolves.size()>0;
        }catch (Exception e){
            return false;
        }
    }


}
