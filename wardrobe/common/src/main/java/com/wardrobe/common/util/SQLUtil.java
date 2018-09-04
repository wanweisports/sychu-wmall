package com.wardrobe.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLUtil {

    public static Map getInToSQL(String inKey, Object ...paramObj){
        Map param = new HashMap();
        if(paramObj != null && paramObj.length > 0) {
            List<Object> list = new ArrayList();
            for(int i = 0; i < paramObj.length; i++){
            	Object paramValue = paramObj[i];
                if(paramValue != null) {
                	if(paramValue.toString().matches("\\d*")){
                		list.add(Integer.valueOf(paramValue.toString()));
                	}else if(paramValue instanceof Integer){
                		list.add(StrUtil.trim(paramValue.toString()));
                	}
                }
            }
            param.put(inKey, list.toArray());
        }
        return param;
    }

    public static Map getInToSQL(String inKey, String paramStrs){
        return getInToSQL(inKey, com.wardrobe.common.util.StrUtil.isNotBlank(paramStrs) ? paramStrs.split(",") : null);
    }

    public static String conversionSqlPlaceholder(String strs, List<Object> params){
        String[] strss = strs.split(",");
        String placeholder = "";
        for(String str : strss){
            if(StringUtils.isNotBlank(placeholder)){
                placeholder += ",";
            }
            placeholder += "?";
            params.add(str);
        }
        return placeholder;
    }

}
