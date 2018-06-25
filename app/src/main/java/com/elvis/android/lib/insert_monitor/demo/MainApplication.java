package com.elvis.android.lib.insert_monitor.demo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.elvis.android.insert_monitor.IJson;
import com.elvis.android.insert_monitor.InsertMonitor;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.json.JSONObject;

/**
 * Created by conghongjie on 2018/6/25.
 */

public class MainApplication extends Application {



    class MyGsonJson implements IJson{
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        @Override
        public String getStringJsonValue(String json, String key) {
            com.google.gson.JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
            return jsonObject.get(key).getAsString();
        }

        @Override
        public <T> T fromJson(String json, Class<T> classOfT) {
            return gson.fromJson(json,classOfT);
        }

        @Override
        public String toJson(Object object) {
            return gson.toJson(object);
        }
    }

    class MyFastJson implements IJson{
        @Override
        public String getStringJsonValue(String json, String key) {
            com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(json);
            return jsonObject.getString(key);
        }

        @Override
        public <T> T fromJson(String json, Class<T> classOfT) {
            T t = (T) JSON.parse(json);
            return t;
        }

        @Override
        public String toJson(Object object) {
            return JSON.toJSONString(object);
        }
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        InsertMonitor.init(this,base,new MyGsonJson());
        InsertMonitor.start();

    }






}
