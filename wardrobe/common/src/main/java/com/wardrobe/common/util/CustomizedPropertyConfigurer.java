package com.wardrobe.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CustomizedPropertyConfigurer extends PropertyPlaceholderConfigurer {  
  
    private static Map<String, Object> ctxPropertiesMap;  
   
    @Override  
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)throws BeansException {  
        super.processProperties(beanFactory, props);  
        ctxPropertiesMap = new HashMap<String, Object>();  
        for (Object key : props.keySet()) {  
            String keyStr = key.toString();  
            String value = props.getProperty(keyStr);  
            ctxPropertiesMap.put(keyStr, value);  
        }  
    }
  
    public static Object getContextProperty(String name) {
        return ctxPropertiesMap.get(name);  
    }
    
    public static Object getImgHttpPath(String imgName){
    	return getImgHttpPath() + imgName;
    }
    
    public static Object getImgHttpPath(){
    	return ctxPropertiesMap.get("path_http").toString() + getPhotoPath();
	}
    
    public static Object getPhotoPath(){
    	return ctxPropertiesMap.get("path_photo");
    }
    
} 