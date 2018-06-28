package com.elvis.android.insert_monitor.plugin.aspectjx.utils;

import android.os.Looper;

/**
 * Created by conghongjie on 2018/6/27.
 */

public class ThreadUtils {



    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
