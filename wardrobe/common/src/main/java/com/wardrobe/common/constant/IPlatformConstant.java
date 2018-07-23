package com.wardrobe.common.constant;

import com.wardrobe.common.util.CustomizedPropertyConfigurer;


/*
 * 自定义常量
 */
public interface IPlatformConstant {
	
	final String SUCCESS_CODE = "1";
	
	final String SUCCESS_MESSAGE = "操作成功";
	
	final String FAIL_CODE = "-1";
	
	final String FAIL_MESSAGE = "操作失败";
	
	final String REQUEST_JSON = "request_json";
	
	String time00 = " 00:00:00";
	
    String time24 = " 23:59:59";
    
    String LOGIN_USER = "loginUser";
    
    String ADMIN = "admin";
    
    String ADMIN_NAME = "管理员";
    
    String DOU_HAO = ",";
    
    String LOGIN_TYPE_PC = ""; //PC端
    
    String LOGIN_TYPE_MOBILE = "/mobile"; //C端
    
    String LOGIN_TYPE_BUSINESS = "/business"; //移动端
    
    String ORDER_SITE_NAME = ""; //场地预定
    
    int SITE_ADVANCE_START_TIME = -20; //场地开场可以提前20分钟签到
    
    int SITE_LATE_START_TIME = 20; //场地开场可以晚20分钟签到
    
    String XIANJIN = "xianjin";
    String ZHIFUBAO = "zhifubao";
    String WEIXIN = "weixin";
    String YINLIAN = "yinlian";
    String ZHIPIAO = "zhipiao";
	
	final String APP_ID = (String) CustomizedPropertyConfigurer.getContextProperty("app_id"); //appid
	
	final String APP_SECRET = (String) CustomizedPropertyConfigurer.getContextProperty("appsecret"); //密钥
	
	
	final String EXCEL_EXTENSION = ".xls";
    final String EXCEL_EXTENSION_X = ".xlsx";
    
    final String WX_OPEN_ID_KEY = "openId";
	
}