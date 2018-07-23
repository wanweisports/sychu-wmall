package com.wardrobe.common.util;

import com.google.gson.*;
import com.wardrobe.common.annotation.GsonExclude;
import org.apache.commons.collections.MapUtils;

import java.lang.reflect.Type;
import java.util.*;

public class JsonUtils {

    private static Gson jsonCreator = null;
    private static Gson jsonDateFormat = null;
    static  {
    	//不过滤
        jsonCreator = new GsonBuilder()
                .registerTypeAdapter(Integer.class, new JsonSerializer<Integer>() {
                    @Override
                    public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(StrUtil.objToStr(src));
                    }
                }).setDateFormat(DateUtil.YYYYMMDDHHMMSS).create();

        //过滤
        jsonDateFormat = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes field) {
                if (field.getAnnotation(GsonExclude.class) == null) {
                    return false;
                }
                return true;
            }
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).registerTypeAdapter(Integer.class, new JsonSerializer<Integer>() {
            @Override
            public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(StrUtil.objToStr(src));
            }
        }).setDateFormat(DateUtil.YYYYMMDDHHMMSS).create();
    }

    public static String toJson(Object obj){
        return jsonCreator.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz){
        return jsonCreator.fromJson(json, clazz);
    }

    public static <T> T fromJson(Map<String, Object> map, Class<T> clazz){
        return jsonCreator.fromJson(toJson(map), clazz);
    }

    public static Map<String, Object> fromJson(Object obj){
        return jsonCreator.fromJson(toJson(obj), Map.class);
    }

    public static Map<String, String> fromJsonToMapStr(Object obj){
        return jsonCreator.fromJson(toJson(obj), Map.class);
    }

    //时间格式化Gson方法
    public static String toJsonDF(Object obj){
        return jsonDateFormat.toJson(obj);
    }

    public static <T> T fromJsonDF(String json, Class<T> clazz){
        return jsonDateFormat.fromJson(json, clazz);
    }

    public static <T> T fromJsonDF(Map<String, Object> map, Class<T> clazz){
        return jsonDateFormat.fromJson(toJsonDF(map), clazz);
    }

    public static <T> List<T> stringToArray(List list, Class<T[]> clazz) {
        return stringToArray(toJsonDF(list), clazz);
    }
    
    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = jsonDateFormat.fromJson(s, clazz);
        return Arrays.asList(arr);
    }

    public static <T> T fromJsonDF(String json, Type type){
        return jsonDateFormat.fromJson(json, type);
    }

    public static <T> T fromJsonDF(Map<String, Object> map, Type type){
        return fromJsonDF(toJsonDF(map), type);
    }

    public static Map<String, Object> fromJsonDF(Object obj){
        return jsonDateFormat.fromJson(toJsonDF(obj), Map.class);
    }

    public static Map<String, String> fromJsonToMapStrDF(Object obj){
        return jsonDateFormat.fromJson(toJsonDF(obj), Map.class);
    }

    public static Map<String, Object> arraysToJsonMap(String json){
        Map<String, Object> map = fromJsonDF(json, Map.class);
        Map resultMap = new HashMap();
        if(MapUtils.isNotEmpty(map)){
            for(String key : map.keySet()){
                Object value = map.get(key);
                if(!(value instanceof List)){
                    resultMap.put(key, value);
                }else{ //横向解析
                    String objMapKey = key.substring(0, key.indexOf("."));
                    String objName = key.substring(key.indexOf(".") + 1);
                    List listValue = (List) value;
                    if (!resultMap.containsKey(objMapKey)) {
                        List resultList = new ArrayList();
                        for (Object lv : listValue) {
                            Map newObj = new HashMap();
                            newObj.put(objName, lv);
                            resultList.add(newObj);
                        }
                        resultMap.put(objMapKey, resultList);
                    }else{
                        List<Map> existObjs = (List<Map>) resultMap.get(objMapKey);
                        for(int i=0; i < existObjs.size(); i++){                    //int size = existObjs.size() >= listValue.size() ? existObjs.size() : listValue.size();
                            existObjs.get(i).put(objName, listValue.get(i));
                        }
                    }
                }
            }
        }
        return resultMap;
    }

}
