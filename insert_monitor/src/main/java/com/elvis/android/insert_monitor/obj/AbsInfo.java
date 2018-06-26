package com.elvis.android.insert_monitor.obj;

import android.support.annotation.CallSuper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by conghongjie on 2018/6/21.
 */

public abstract class AbsInfo {


    String dataType;
    long dataTime;

    public AbsInfo(long dataTime){
        this.dataType = this.getClass().getSimpleName();
        this.dataTime = dataTime;
    }



    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }
}
