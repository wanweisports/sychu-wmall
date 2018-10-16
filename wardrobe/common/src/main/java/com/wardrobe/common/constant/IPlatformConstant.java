package com.wardrobe.common.constant;

import com.wardrobe.common.util.CustomizedPropertyConfigurer;


/*
 * 自定义常量
 */
public interface IPlatformConstant {

	String excelExtension = ".xls";
	String excelExtensionX = ".xlsx";
	
	String SUCCESS_CODE = "1";
	
	String SUCCESS_MESSAGE = "操作成功";
	
	String FAIL_CODE = "-1";
	
	String FAIL_MESSAGE = "操作失败";

    String FAIL_NOT_LOGIN_CODE = "10"; //未登录

	String FAIL_NOT_PERFECT_CODE = "20"; //未完善资料
	
	String REQUEST_JSON = "request_json";
	
	String time00 = " 00:00:00";
	
    String time24 = " 23:59:59";
    
    String LOGIN_USER = "LOGIN_USER";

	String AND = "&";
    
    String ADMIN = "admin";
    
    String ADMIN_NAME = "管理员";
    
    String DOU_HAO = ",";
	
	String APP_ID = (String) CustomizedPropertyConfigurer.getContextProperty("app_id"); //appid
	
	String APP_SECRET = (String) CustomizedPropertyConfigurer.getContextProperty("appsecret"); //密钥
    
    String WX_OPEN_ID_KEY = "openId";

	int ADD_USER_YCOID = 1;

	int ADD_USER_SCORE = 1;

	int INIT_USER_SCORE = 0;

	int FREIGHT = 12; //两件包邮，其余运费写12

	String UNDERLINE = "_";

	String ONO = "NO";

	String RNO = "MK";

	String OG_IMG_SUFFIX = "_OG"; //原图后缀

}