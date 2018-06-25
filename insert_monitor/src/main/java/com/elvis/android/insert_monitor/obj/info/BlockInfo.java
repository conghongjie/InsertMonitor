package com.elvis.android.insert_monitor.obj.info;

import com.elvis.android.insert_monitor.obj.AbsInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by conghongjie on 2018/6/21.
 */

public class BlockInfo extends AbsInfo {

    long startTime;
    long endTime;

    public BlockInfo(long dataTime) {
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
