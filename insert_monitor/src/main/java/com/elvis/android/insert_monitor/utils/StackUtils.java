package com.elvis.android.insert_monitor.utils;

/**
 * Created by conghongjie on 2018/6/27.
 */

public class StackUtils {

    public static final String separator = "&&rn&&";

    public static String getStack(Thread thread){
        StringBuilder stackStringBuilder = new StringBuilder();
        for (StackTraceElement stackTraceElement : thread.getStackTrace()) {
            stackStringBuilder.append(stackTraceElement.toString()).append(separator);
        }
        return stackStringBuilder.toString();
    }
}
