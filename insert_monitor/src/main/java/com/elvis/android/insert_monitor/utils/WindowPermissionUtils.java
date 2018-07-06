package com.elvis.android.insert_monitor.utils;

import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;

import java.lang.reflect.Method;

/**
 * Created by conghongjie on 2018/7/6.
 */

public class WindowPermissionUtils {



    /**
     * 判断是否有悬浮窗权限
     * @param context
     * @return
     */
    public static boolean checkFloatWindowPermission(Context context) {
        boolean state = getAppOps(context);
        if (!state) {
            return false;
        }
        if (Build.VERSION.SDK_INT>=23 && !android.provider.Settings.canDrawOverlays(context)) {
            return false;
        }
        return true;
    }

    private static boolean getAppOps(Context context) {
        try {
            Object object = context.getSystemService("appops");
            Class localClass = object.getClass();
            Class[] arrayOfClass = new Class[3];
            arrayOfClass[0] = Integer.TYPE;
            arrayOfClass[1] = Integer.TYPE;
            arrayOfClass[2] = String.class;
            Method method = localClass.getMethod("checkOp", arrayOfClass);
            Object[] arrayOfObject1 = new Object[3];
            arrayOfObject1[0] = Integer.valueOf(24);
            arrayOfObject1[1] = Integer.valueOf(Binder.getCallingUid());
            arrayOfObject1[2] = context.getPackageName();
            int m = ((Integer) method.invoke(object, arrayOfObject1)).intValue();
            return m == AppOpsManager.MODE_ALLOWED;
        } catch (Exception ex) {
        }
        return true;
    }



    public static void goSettingActivity(Context context){
        //6.0以上直接跳转到悬浮窗管理
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            if (ActivityUtils.tryStartActivity(context,intent)){
                return;
            }
        }
        //oppo跳转到手机管家
        if (Build.BRAND.toLowerCase().contains("oppo")){
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // - com.color.safecenter:
            intent.setComponent(ComponentName.unflattenFromString("com.color.safecenter/.permission.floatwindow.FloatWindowListActivity"));
            if (ActivityUtils.tryStartActivity(context,intent)){
                return;
            }
            intent.setComponent(ComponentName.unflattenFromString("com.color.safecenter/.permission.PermissionManagerActivity"));
            if (ActivityUtils.tryStartActivity(context,intent)){
                return;
            }
            intent.setComponent(ComponentName.unflattenFromString("com.color.safecenter/.permission.PermissionTopActivity"));
            if (ActivityUtils.tryStartActivity(context,intent)){
                return;
            }
            intent.setComponent(ComponentName.unflattenFromString("com.color.safecenter/.SecureSafeMainActivity"));
            if (ActivityUtils.tryStartActivity(context,intent)){
                return;
            }
            if (ActivityUtils.tryStartActivity(context,ActivityUtils.getAppMainActivityIntent(context,"com.color.safecenter"))){
                return;
            }
            // - com.coloros.safecenter:
            intent.setComponent(ComponentName.unflattenFromString("com.coloros.safecenter/.sysfloatwindow.FloatWindowListActivity"));
            if (ActivityUtils.tryStartActivity(context,intent)){
                return;
            }
            intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.privacypermissionsentry.PermissionTopActivity"));
            if (ActivityUtils.tryStartActivity(context,intent)){
                return;
            }
            if (ActivityUtils.tryStartActivity(context,ActivityUtils.getAppMainActivityIntent(context,"com.coloros.safecenter"))){
                return;
            }
        }
        //vivo跳转到手机管家
        if (Build.BRAND.toLowerCase().contains("vivo")){
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("packagename",context.getPackageName());
            intent.putExtra("title","请打开\"锁屏显示\"");
            //com.iqoo.secure：
            intent.setComponent(ComponentName.unflattenFromString("com.iqoo.secure/.safeguard.SoftPermissionDetailActivity"));
            if (ActivityUtils.tryStartActivity(context,intent)){
                return;
            }
            //com.vivo.permissionmanager
            intent.setComponent(ComponentName.unflattenFromString("com.vivo.permissionmanager/.activity.SoftPermissionDetailActivity"));
            if (ActivityUtils.tryStartActivity(context,intent)){
                return;
            }
        }
        //系统设置页面
        Intent intent =  new Intent(Settings.ACTION_SETTINGS);
        context.startActivity(intent);
    }





}
