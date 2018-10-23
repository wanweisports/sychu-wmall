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
    String RESOURCE_COMMODITY_BANNER_IMG = "20"; //商品Banner图

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

    String COMM_STATUS = "COMM_STATUS"; //商品状态

    String COMM_STOCK_TYPE = "COMM_STOCK_TYPE"; //商品库存变更类型
    String COMM_STOCK_TYPE_ADD = "10"; //商品库存入库
    String COMM_STOCK_TYPE_SUB = "20"; //商品库存出库
    String COMM_STOCK_TYPE_SALE = "30"; //商品库存销售

    String ORDER_PAY_STATUS = "ORDER_PAY_STATUS"; //订单支付状态
    String ORDER_PAY_STATUS_YES = "1"; //已支付
    String ORDER_PAY_STATUS_NO = "2";  //未支付
    String ORDER_PAY_STATUS_CANCEL = "3";  //已取消

    String ORDER_STATUS = "ORDER_STATUS"; //订单状态
    String ORDER_STATUS_NORMAL = "1";     //正常
    String ORDER_STATUS_RETURN_ING = "2"; //申请退款
    String ORDER_STATUS_RETURN = "3";     //退款成功
    String ORDER_STATUS_REFUSE = "4";     //退款拒绝

    String USER_COUPON = "USER_COUPON"; //优惠券类型
    String COUPON_SERVICE_TYPE = "100008"; //满减优惠类型

    String RESERVE_ORDER_STATUS = "RESERVE_ORDER_STATUS"; //预约单状态
    String RESERVE_ORDER_STATUS_YES = "1"; //正常
    String  RESERVE_ORDER_STATUS_NO = "2"; //取消

}
