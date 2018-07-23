package com.wardrobe.common.util;



public class UrlConvertor {

	/**
	 * @return
	 * @Description:获取物理路径
	 */
	public static String weixinResource(){
		
		String wuli_path = (String) CustomizedPropertyConfigurer.getContextProperty("weixin_resource");
		
		return wuli_path;
	}
	
	
}
