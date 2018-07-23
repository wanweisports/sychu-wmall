package com.wardrobe.controller;

import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.po.UserOperator;
import com.wardrobe.platform.service.IOperatorService;
import com.wardrobe.wx.WeiXinConnector;
import com.wardrobe.wx.pojo.WeixinOauth2Token;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@RequestMapping("oAuth2")
@Controller
public class WXOAuth2Controller extends BaseController {
	
	private static Logger logger = Logger.getLogger(WXOAuth2Controller.class);

	@Autowired
	private IOperatorService operatorService;
	
	/**
	 * 微信授权登录
	 */
	@RequestMapping("login")
	public String oAuth2Login(HttpServletRequest request, HttpServletResponse response){
		
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");
		String returnUrl = request.getParameter("returnUrl");
		logger.info(code);
		logger.info(returnUrl);

		// 用户同意授权
		if (!"authdeny".equals(code)) {
			// 获取网页授权access_token,应用的appid和appsecret
			WeixinOauth2Token weixinOauth2Token = WeiXinConnector.getOauth2AccessToken(IPlatformConstant.APP_ID, IPlatformConstant.APP_SECRET, code);
			// 用户标识
			String openId = weixinOauth2Token.getOpenId();
			//根据openId判断用户是否授权登陆过，未授权过，则调用获取用户信息微信接口
			UserOperator userOperator = null;//operatorService.getUserOperatorByOpenId(openId);
			HttpSession session = request.getSession();
			session.setAttribute(IPlatformConstant.WX_OPEN_ID_KEY, openId);
			UserOperator userInfo = getUserInfo();
			if(userInfo != null){
				if(userOperator == null){ //已登录并不存在openId的先保存
					//userOperator = operatorService.updateUserOperatorOpenId(userInfo.getOperatorId(), openId);
				}
			}
			if(userOperator != null){
				super.getRequest().getSession().setAttribute(IPlatformConstant.LOGIN_USER, userOperator);
			}
		}
		return "";
	}

}
