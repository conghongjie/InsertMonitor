package com.elvis.android.insert_monitor.obj.info;

import com.elvis.android.insert_monitor.obj.AbsInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by conghongjie on 2018/6/25.
 */

public class ThreadCpuInfo extends AbsInfo {

    public String threadId;
    public String threadName;
    public long threadCpu;

    public ThreadCpuInfo(long dataTime) {
        super(dataTime);
    }


    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public long getThreadCpu() {
        return threadCpu;
    }

    public void setThreadCpu(long threadCpu) {
        this.threadCpu = threadCpu;
    }
}