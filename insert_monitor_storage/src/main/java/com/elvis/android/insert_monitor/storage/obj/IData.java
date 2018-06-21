package com.elvis.android.insert_monitor.storage.obj;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by conghongjie on 2018/6/20.
 */

public interface IData {

    void fromJson(JSONObject json);

    JSONObject toJson() throws JSONException;

}
