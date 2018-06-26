package com.elvis.android.insert_monitor.obj.info;

import com.elvis.android.insert_monitor.obj.AbsInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by conghongjie on 2018/6/20.
 */

public class SMInfo extends AbsInfo {


    public long time;
    public long sm;

    public SMInfo(long dataTime) {
        super(dataTime);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getSm() {
        return sm;
    }

    public void setSm(long sm) {
        this.sm = sm;
    }
}
