package com.wardrobe.common.constant;

public interface IDBConstant {

	int NULL_PARENT_KEY = -1;// 空父字典键
	
	String LOGIC_STATUS = "LOGIC_STATUS";//逻辑状态

    String LOGIC_STATUS_YES = "1";//是
    String LOGIC_STATUS_NO = "2";//否
    String LOGIC_STATUS_OTHER = "3"; //其他

    String OPERATOR_TYPE_ADMIN = "1";//管理员
    String OPERATOR_TYPE_WORK = "2";//操作员
    String OPERATOR_TYPE_USER = "3";//会员
    
    String STATUS = "STATUS";//状态

    String STATUS_YES = "1";//有效
    String STATUS_NO = "2";//无效
    
    String SEX = "SEX";//性别

    String SEX_NAN = "1";//男
    String SEX_NV = "2";//女
    
    String BALANCE_STYLE = "BALANCE_STYLE";//支付方式

    String BALANCE_STYLE_XJ = "1";//现金
    String BALANCE_STYLE_ZFB = "2";//支付宝
    String BALANCE_STYLE_WX = "3";//微信
    public final String BALANCE_STYLE_YINLIAN = "4";//银联
    public final String BALANCE_STYLE_ZHIPIAO = "5";//支票
    
    int ZERO = 0;
    
    String BALANCE_TYPE = "BALANCE_TYPE";//流水类型
    String BALANCE_TYPE_CZ = "1";//充值
    String BALANCE_TYPE_XF = "2";//消费
    String BALANCE_TYPE_OTHER = "3";//其他
    
    String BALANCE_SERVICE_TYPE = "BALANCE_SERVICE_TYPE";//业务类型
    String BALANCE_SERVICE_TYPE_REG = "10";//注册会员
    String BALANCE_SERVICE_TYPE_CARD_CZ = "11";//会员充值
    String BALANCE_SERVICE_TYPE_CARD_UP = "12";//升级会员卡
    String BALANCE_SERVICE_TYPE_CARD_BUBAN = "13";//补办会员卡
    String BALANCE_SERVICE_TYPE_CARD_BUBAN_TEACHER = "20";//教师补办会员卡
    String BALANCE_SERVICE_TYPE_CARD_BUBAN_STUDENT = "30";//学生补办会员卡
    
    String BALANCE_SERVICE_TYPE_SITE = "100";//场地预定
    String BALANCE_SERVICE_TYPE_BLOCK_SITE = "200";//包场预订
    String BALANCE_SERVICE_TYPE_SITE_RECEIVABLE = "220";//应收款
    String BALANCE_SERVICE_TYPE_GOODS = "300";//商品
    
    
    
    String BALANCE_STATUS = "BALANCE_STATUS";//流水状态
    String BALANCE_STATUS_ALL = "1";//已收全款
    String BALANCE_STATUS_NOT = "2";//未付款
    String BALANCE_STATUS_ONE = "3";//部分收款
    
    String GOOD_STATE = "GOOD_STATE";//支付方式

    String GOOD_STATE_ING = "1";//在售
    String GOOD_STATE_BOOKING = "2";//预售
    String GOOD_STATE_OUT = "3";//下架
    
    int ROLE_ADMIN = 1;//超级管理员
    int ROLE_MEMBER = 2;//会员
    int ROLE_COMMON = 3;//普通用户
    
    String ORDER_SERVICE_TYPE = "ORDER_SERVICE_TYPE";//订单业务类型
    String ORDER_SERVICE_TYPE_SITE = "100";//场地预定
    String ORDER_SERVICE_TYPE_BLOCK_SITE = "200";//包场预订
    String ORDER_SERVICE_TYPE_GOODS = "300";//商品消费

    int ROLE_EMPLOYEE = 1;//员工角色最低主键值

    // 通知
    String NOTIFICATIONS_SENDER_YES = "1";//已发送
    String NOTIFICATIONS_SENDER_NO = "2";//未发送
    String NOTIFICATIONS_SENDER_DEL = "3";//已删除

    String NOTIFICATIONS_RECEIVER_YES = "1";//已读
    String NOTIFICATIONS_RECEIVER_NO = "2";//未读
    String NOTIFICATIONS_RECEIVER_DEL = "3";//已删除
    String NOTIFICATIONS_RECEIVER_ALL = "4";//已读

    String NOTIFICATIONS_TYPE_DRAFT = "1"; // 草稿
    String NOTIFICATIONS_TYPE_SEND = "2"; // 发件箱
    String NOTIFICATIONS_TYPE_RECEIVE = "3"; // 收件箱
    String NOTIFICATIONS_TYPE_TRASH = "4"; // 垃圾箱

    String NOTIFICATIONS_TYPE_RECEIVE_UNREAD = "31"; // 未读收件箱
    String NOTIFICATIONS_TYPE_RECEIVE_READ = "32"; // 已读收件箱
    
    String CARD_TEACHERS = "1";//教师卡
    String CARD_TEAM = "2";//团体会员卡
    String CARD_STUDENT = "3"; //学生卡
    
    String SIGN_STATUS_IN = "1";//已签到
    String SIGN_STATUS_OVER = "2";//已过期
    String SIGN_STATUS_NORMAL = "3"; //未使用
    
    String INVENTORY_OP_TYPE_ADD = "1";//添加
    String INVENTORY_OP_TYPE_IN = "2";//入库
    String INVENTORY_OP_TYPE_OUT = "3"; //销售
    String INVENTORY_OP_TYPE_MINUS = "4"; //损耗

    String INVOICE_TYPE_SPECIAL = "1";// 专票
    String INVOICE_TYPE_GENERAL = "2"; //普票

    int DATA_DATE_PRE_DAY = 10; // 昨天
    int DATA_DATE_DAY = 11; // 今天
    int DATA_DATE_PRE_WEEK = 20; // 上周
    int DATA_DATE_WEEK = 21; // 本周
    int DATA_DATE_NEXT_WEEK = 22; // 下周
    int DATA_DATE_PRE_MONTH = 30; // 上月
    int DATA_DATE_MONTH = 31; // 本月
    int DATA_DATE_PRE_YEAR = 40; // 上年
    int DATA_DATE_YEAR = 41; // 本年
}
