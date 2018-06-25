package com.elvis.android.insert_monitor.obj.pack;

import com.elvis.android.insert_monitor.obj.AbsInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 数据封装
 * 1、存储格式
 * 2、传输格式
 *
 * Created by conghongjie on 2018/6/21.
 */

public class DataPack {


    long id;
    long time;
    String type;
    JSONObject info;



//    public static DataPack build(long id, long time, AbsInfo info) throws JSONException {
//        DataPack dataPack = new DataPack();
//        dataPack.id = id;
//        dataPack.time = time;
//        dataPack.type = info.getClass().getName();
//        dataPack.info = info.toJson();
//        return dataPack;
//    }
//
//
//    public void fromJson(JSONObject json) {
//        id = json.optLong("id",0L);
//        time = json.optLong("time",0L);
//        type = json.optString("type","");
//        info = json.optJSONObject("info");
//    }
//
//    public JSONObject toJson() throws JSONException {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("id",id);
//        jsonObject.put("time",time);
//        jsonObject.put("type",type);
//        jsonObject.put("info",info);
//        return jsonObject;
//    }
}
