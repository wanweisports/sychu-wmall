package com.wardrobe.common.constant;

public interface IDBConstant {

	int NULL_PARENT_KEY = -1;// 空父字典键
	
	String LOGIC_STATUS = "LOGIC_STATUS";//逻辑状态

    String LOGIC_STATUS_YES = "1";//是
    String LOGIC_STATUS_NO = "2";//否
    String LOGIC_STATUS_OTHER = "3"; //其他

    int ZERO = 0;

    String RESOURCE_TYPE_IMG = "10"; //图片
    String RESOURCE_TYPE_FILE = "20"; //文件

    String RESOURCE_COMMODITY_IMG = "10"; //商品图

    String USER_SEX = "USER_SEX";//性别
    String USER_SEX_MALE = "10";//性别男
    String USER_SEX_FEMALE = "20";//性别女

    String USER_AGE = "USER_AGE";//年龄

    String COMM_CATEGORY = "COMM_CATEGORY"; //品类
    String COMM_STYLE = "COMM_STYLE"; //风格
    String COMM_MATERIAL = "COMM_MATERIAL"; //材质

    String USER_SIZE = "USER_SIZE"; //尺码偏好

    String TRANSACTIONS_TYPE = "TRANSACTIONS_TYPE"; //流水类型
    String TRANSACTIONS_TYPE_ZF = "10"; //支付
    String TRANSACTIONS_TYPE_CZ = "20"; //充值
    String TRANSACTIONS_TYPE_TK = "30"; //退款


    String RECHARGE_TYPE = "RECHARGE_TYPE"; //充值大小

    String USER_COUPON = "USER_COUPON"; //优惠券类型

    String COMM_STATUS = "COMM_STATUS"; //商品状态

    String COMM_STOCK_TYPE = "COMM_STOCK_TYPE"; //商品库存变更类型
    String COMM_STOCK_TYPE_ADD = "10"; //商品库存增加
    String COMM_STOCK_TYPE_SUB = "20"; //商品库存减少

}
