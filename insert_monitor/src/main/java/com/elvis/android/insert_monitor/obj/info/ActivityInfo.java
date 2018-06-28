package com.elvis.android.insert_monitor.obj.info;

import com.elvis.android.insert_monitor.obj.AbsInfo;

import java.util.ArrayList;

/**
 * Created by conghongjie on 2018/6/28.
 */

public class ActivityInfo extends AbsInfo{


    public String activityName;
    public String hashCode;
    public String methodName;
    public long startTime;
    public long endTime;

    public ActivityInfo(long dataTime) {
        super(dataTime);
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
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
