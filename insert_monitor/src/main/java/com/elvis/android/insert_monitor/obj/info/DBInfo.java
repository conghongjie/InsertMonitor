package com.elvis.android.insert_monitor.obj.info;

import com.elvis.android.insert_monitor.obj.AbsInfo;

import java.util.ArrayList;

/**
 * Created by conghongjie on 2018/6/21.
 */

public class DBInfo extends AbsInfo {


    public static final String METHOD_getWritableDatabase = "getWritableDatabase()";


    public long startTime;
    public long endTime;
    public String methodName;
    public String stack;
    public String sql;

    public DBInfo(long dataTime) {
        super(dataTime);
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

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
