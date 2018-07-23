package com.wardrobe.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONObject;

public class GsonUtil {
	
	/**
	 * Gson 只转换添加注解的字段
	 */
	public static Gson getExcludeGson(){ 
		 return GsonFactory.getExcludeGson();
	}
	
	/**
	 * 获取普通Gson对象
	 */
	public static Gson getGson(){
		return GsonFactory.getGson();
	}
	
	/**
	 * @param jsonStr json字符串
	 * @return 使用json库解析json
	 */
	public static JSONObject getJsonObject(String jsonStr){
		 return JSONObject.fromObject(jsonStr);
	}
	
	private static class GsonFactory{
		public static Gson gson = new Gson();
		public static GsonBuilder builder = new GsonBuilder();
		
		private static Gson getGson(){
			return gson;
		}
		
		private static Gson getExcludeGson(){
			 builder.excludeFieldsWithoutExposeAnnotation();
			 return builder.create();
		}
		
	}
	
}
