package com.elvis.android.insert_monitor.obj.info;

import com.elvis.android.insert_monitor.obj.AbsInfo;

/**
 * Created by conghongjie on 2018/6/28.
 */

public class InflateInfo extends AbsInfo{


    public static final String METHOD_inflate =             "inflate(int,ViewGroup)";
    public static final String METHOD_setContentView =      "setContentView(int)";


    public String methodName;

    public String stack;
    public String resource;

    public long startTime;
    public long endTime;

    public InflateInfo(long dataTime) {
        super(dataTime);
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
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
