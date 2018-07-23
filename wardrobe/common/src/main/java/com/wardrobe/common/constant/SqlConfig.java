package com.wardrobe.common.constant;

/**
 * 全局常量配置
 */
public class SqlConfig {
    public static final long SystemId = 0L;//系统id
    public static final int MasterId = -1;//管理员id
    public static final int PageSize = 30;//每页显示条数
    public static final String SelectAllStr = "SELECT *";
    public static final String SelectCountStr = "SELECT COUNT(1)";
    public static final String SelectAllQuery = "^select(.*)from";
    public static final String SelectCountQuery = "select count(1) from";

}
