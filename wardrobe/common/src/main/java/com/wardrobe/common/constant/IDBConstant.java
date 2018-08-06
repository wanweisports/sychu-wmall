package com.wardrobe.common.constant;

public interface IDBConstant {

	int NULL_PARENT_KEY = -1;// 空父字典键
	
	String LOGIC_STATUS = "LOGIC_STATUS";//逻辑状态

    String LOGIC_STATUS_YES = "1";//是
    String LOGIC_STATUS_NO = "2";//否
    String LOGIC_STATUS_OTHER = "3"; //其他
    
    String SEX = "SEX";//性别

    int ZERO = 0;

    String RESOURCE_COMMODITY_IMG = "10"; //商品图

    String COMM_CATEGORY = "COMM_CATEGORY"; //品类
    String COMM_STYLE = "COMM_STYLE"; //风格
    String COMM_MATERIAL = "COMM_MATERIAL"; //材质

    String TRANSACTIONS_TYPE = "TRANSACTIONS_TYPE"; //流水类型
    String TRANSACTIONS_TYPE_ZF = "10"; //支付
    String TRANSACTIONS_TYPE_CZ = "20"; //充值
    String TRANSACTIONS_TYPE_TK = "30"; //退款


    String RECHARGE_TYPE = "RECHARGE_TYPE"; //充值大小

}
