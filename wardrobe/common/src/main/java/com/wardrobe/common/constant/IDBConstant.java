package com.wardrobe.common.constant;

public interface IDBConstant {

	int NULL_PARENT_KEY = -1;// 空父字典键
	
	String LOGIC_STATUS = "LOGIC_STATUS";//逻辑状态

    String LOGIC_STATUS_YES = "1";//是
    String LOGIC_STATUS_NO = "2";//否
    String LOGIC_STATUS_OTHER = "3"; //其他
    
    String STATUS = "STATUS";//状态

    String STATUS_YES = "1";//有效
    String STATUS_NO = "2";//无效
    
    String SEX = "SEX";//性别

    String SEX_NAN = "1";//男
    String SEX_NV = "2";//女
    
    String BALANCE_TYPE = "BALANCE_TYPE";//流水类型
    String BALANCE_TYPE_CZ = "1";//充值
    String BALANCE_TYPE_XF = "2";//消费
    String BALANCE_TYPE_OTHER = "3";//其他

    int ZERO = 0;

    String RESOURCE_COMMODITY_IMG = "10"; //商品图

    String COMM_CATEGORY = "COMM_CATEGORY"; //品类
    String COMM_STYLE = "COMM_STYLE"; //风格
    String COMM_MATERIAL = "COMM_MATERIAL"; //材质

}
