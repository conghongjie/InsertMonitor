package com.elvis.android.insert_monitor.obj.info;

import com.elvis.android.insert_monitor.obj.AbsInfo;


import java.util.ArrayList;

/**
 * Created by conghongjie on 2018/6/24.
 */

public class BaseInfo extends AbsInfo{

    //cpu
    public long systemCpu;
    public long processCpu;
    public ArrayList<ThreadCpuInfo> threadCpus;
    //memory
    public long processMemory;
    //flow
    public long flowUpload;
    public long flowDownload;


    public BaseInfo(long dataTime) {
        super(dataTime);
    }


    public long getSystemCpu() {
        return systemCpu;
    }

    public void setSystemCpu(long systemCpu) {
        this.systemCpu = systemCpu;
    }

    public long getProcessCpu() {
        return processCpu;
    }

    public void setProcessCpu(long processCpu) {
        this.processCpu = processCpu;
    }

    public ArrayList<ThreadCpuInfo> getThreadCpus() {
        return threadCpus;
    }

    public void setThreadCpus(ArrayList<ThreadCpuInfo> threadCpus) {
        this.threadCpus = threadCpus;
    }

    public long getProcessMemory() {
        return processMemory;
    }

    public void setProcessMemory(long processMemory) {
        this.processMemory = processMemory;
    }

    public long getFlowUpload() {
        return flowUpload;
    }

    public void setFlowUpload(long flowUpload) {
        this.flowUpload = flowUpload;
    }

    public long getFlowDownload() {
        return flowDownload;
    }

    public void setFlowDownload(long flowDownload) {
        this.flowDownload = flowDownload;
    }
}
