package com.elvis.android.insert_monitor;

/**
 * Created by conghongjie on 2018/6/25.
 */

public interface IJson {


    String getStringJsonValue(String json,String key);

    <T> T fromJson(String json, Class<T> classOfT);

    String toJson(Object object);



}
