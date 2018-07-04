package com.elvis.android.insert_monitor.obj.info;

import com.elvis.android.insert_monitor.obj.AbsInfo;

/**
 * Created by conghongjie on 2018/6/25.
 */

public class MessageInfo extends AbsInfo {


    public long startTime;
    public long endTime;

    public MessageInfo(long dataTime) {
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
}
