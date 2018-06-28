package com.elvis.android.insert_monitor.obj.info;

import com.elvis.android.insert_monitor.obj.AbsInfo;

import java.util.ArrayList;

/**
 * Created by conghongjie on 2018/6/28.
 */

public class ActivityInfo extends AbsInfo{



    public static final String METHOD_onCreate =        "onCreate(Bundle)";
    public static final String METHOD_onCreate_2 =      "onCreate(Bundle,PersistableBundle)";
    public static final String METHOD_onStart =         "onStart()";
    public static final String METHOD_onResume =        "onResume()";
    public static final String METHOD_onPause =         "onPause()";
    public static final String METHOD_onStop =          "onStop()";
    public static final String METHOD_onDestroy =       "onDestroy()";

    public String className;
    public String hashCode;
    public String methodName;
    public long startTime;
    public long endTime;

    public ActivityInfo(long dataTime) {
        super(dataTime);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
