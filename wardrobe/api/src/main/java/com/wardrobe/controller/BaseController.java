package com.wardrobe.controller;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.enum_.MobileMessageEnum;
import com.wardrobe.common.po.UserInfo;
import com.wardrobe.common.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
	public static final String REQUEST_HEAD = "text/html; charset=UTF-8";
	
	public static final String APPLICATION_JSON = "application/json";
    
	public static final String CONTENT_TYPE_TEXT_JSON = "text/json";

	@Value("gatePort")
	public int gatePort;

	@Value("gateIp")
	public String gateIp;

	@Value("relayPort")
	public int relayPort;

	@Value("relayIp")
	public String relayIp;

	@Value("rfidPort")
	public int rfidPort;

	@Value("rfidIp")
	public String rfidIp;

	protected String redirect(String path) {
        return new StringBuilder(UrlBasedViewResolver.REDIRECT_URL_PREFIX).append(path).toString();
    }

    protected String forward(String path) {
        return new StringBuilder(UrlBasedViewResolver.FORWARD_URL_PREFIX).append(path).toString();
    }

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    protected UserInfo getUserInfo() {
		return (UserInfo) getRequest().getSession().getAttribute(IPlatformConstant.LOGIN_USER);
    }

	protected boolean checkMobileMessage(MobileMessageEnum mobileMessageEnum, String mobile, String code){
		HttpSession session = getRequest().getSession();
		String mobileSessionKey = mobileMessageEnum.name;
		System.out.println(session.getAttribute(mobileSessionKey));
		if((mobile + IPlatformConstant.AND + code).equals(session.getAttribute(mobileSessionKey))){
			session.removeAttribute(mobileSessionKey);
			return true;
		}
		return false;
	}

	protected Map setPageInfo(PageBean pageBean){
		Map map = new HashMap<>(5, 1);
		map.put("list", pageBean.getList());
		map.put("count", pageBean.getTotalCount());
		map.put("lastPage", pageBean.getTotalPage());
		map.put("currentPage", pageBean.getCurrentPage());
		map.put("pageSize", pageBean.getPageSize());
		return map;
	}
    
    protected String getStreamResult(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		InputStream in=request.getInputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len=in.read(buffer))!=-1){
			out.write(buffer, 0, len);
		}
		out.close();
		in.close();
		return new String(out.toByteArray(),"utf-8");
	}
    
}
