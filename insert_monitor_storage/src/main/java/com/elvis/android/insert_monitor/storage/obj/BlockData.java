package com.elvis.android.insert_monitor.storage.obj;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by conghongjie on 2018/6/20.
 */

public class BlockData implements IData {


    long startTime;
    long endTime;

    @Override
    public void fromJson(JSONObject json) {
        startTime = json.optLong("start_time",0L);
        endTime = json.optLong("end_time",0L);
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("start_time",startTime);
        json.put("end_time",endTime);
        return json;
    }
}
