package com.dataoke.client.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * json管理
 *
 * @author ddllxy
 */
public class JsonUtils {


    public static Map<String, Object> json2Map(String json) {
        return new Gson().fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());
    }

    public static <T> T jsonStrToObject(String json, Class<T> classOfT) {
        return new Gson().fromJson(json, classOfT);
    }

    public static <T> T jsonStrToObject(String json, Type typeOfT) {
        return new Gson().fromJson(json, typeOfT);
    }

    public static String objectToJsonStr(Object object) {
        return new Gson().toJson(object);
    }

    //对象转换类型
    public static <T> T objectToObject(Object o, Class<T> classOfT) {
        return jsonStrToObject(objectToJsonStr(o), classOfT);
    }

    //对象转换类型
    public static <T> T objectToObject(Object o, Type typeOfT) {
        return jsonStrToObject(objectToJsonStr(o), typeOfT);
    }

}
