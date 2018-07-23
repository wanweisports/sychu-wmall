package com.wardrobe.common.bean;

import com.wardrobe.common.constant.IPlatformConstant;

import java.util.HashMap;
import java.util.Map;

public class ResponseBean {
	
	private String code;
	private String message;
	private Map<String, Object> data = new HashMap<String, Object>();
	
	public ResponseBean() {
		
	}
	
	public ResponseBean(Map<String, Object> data) {
		this.code = IPlatformConstant.SUCCESS_CODE;
		this.message = IPlatformConstant.SUCCESS_MESSAGE;
		this.data.putAll(data != null ? data : new HashMap());
	}
	
	public ResponseBean(boolean isSuccess) {
		this.code = isSuccess ? IPlatformConstant.SUCCESS_CODE : IPlatformConstant.FAIL_CODE;
		this.message = isSuccess ? IPlatformConstant.SUCCESS_MESSAGE : IPlatformConstant.FAIL_MESSAGE;
	}
	
	public ResponseBean(String message) {
		this.code = IPlatformConstant.FAIL_CODE;
		this.message = message;
	}
	
	public ResponseBean(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
