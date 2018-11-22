package com.wardrobe.wx;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.TemplateData;
import com.wardrobe.common.bean.WxMssBean;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.constant.XcxConstant;
import com.wardrobe.common.po.UserAccount;
import com.wardrobe.common.po.UserOrderDetail;
import com.wardrobe.common.po.UserOrderInfo;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.wx.http.HttpConnect;
import com.wardrobe.wx.pojo.AccessToken;
import com.wardrobe.wx.pojo.WeixinOauth2Token;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class WeiXinConnector {
	
	private static Logger logger = Logger.getLogger(WeiXinConnector.class);

	public static final String CORP_ID = "";//企业号的CorpID
	
	public static final String APP_ID = IPlatformConstant.APP_ID;//"wx3b705c91dccc6f35";//"wx86c89d3d834ee891";//"wx48ce782a498f4a72";//"wx86c89d3d834ee891";//APP_ID
	
	public static final String MCH_ID = "";//商户号mch_id
	
	public static final String KEY = "";//商户号mch_id
	
	public static final String SECRET = IPlatformConstant.APP_SECRET;

	//企业号TOKEN
	//private static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
	
	//非企业号TOKEN
	public final static String ACCESS_TOKEN_URL = XcxConstant.ACCESS_TOKEN_URL;

	
	public static AccessToken accessToken = null;
	
	public static Long lastGetAccessTokenTime = null;
	
	/**
	 * 接口Token
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		if(accessToken == null || ((System.currentTimeMillis() - lastGetAccessTokenTime) / 1000) > 7000) { //7200秒过期
			String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace("SECRET", appsecret);
			JSONObject jsonObject = HttpConnect.httpsRequest(requestUrl, "GET", null);
			logger.info("Token jsonObject===>" + jsonObject.toString());
			// 如果请求成功
			if (null != jsonObject) {
				try {
					accessToken = new AccessToken();
					accessToken.setToken(jsonObject.getString("access_token"));
					accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
					lastGetAccessTokenTime = System.currentTimeMillis();
				} catch (JSONException e) {
					accessToken = null;
					// 获取token失败
					e.printStackTrace();
					//log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
				}
			}
		}
    	return accessToken;
    }

	@Desc("模板ID\n" +
			"fWMHzkV10pTOxjLkQcdQI6CBQ3ReR0UqIYqj04SPPNI\n" +
			"标题\n" +
			"订单支付成功通知\n" +
			"关键词\n" +
			"订单号码\n" +
			"{{keyword1.DATA}}\n" +
			"订单金额\n" +
			"{{keyword2.DATA}}\n" +
			"支付时间\n" +
			"{{keyword3.DATA}}\n" +
			"物品名称\n" +
			"{{keyword4.DATA}}")
	public static JSONObject sendOrderPaySuccess(String appid, String appsecret, UserOrderInfo userOrderInfo, String toUserOpenId){
		AccessToken accessToken = getAccessToken(appid, appsecret);
		WxMssBean wxMssBean = new WxMssBean();
		wxMssBean.setForm_id(userOrderInfo.getPrepayId());
		wxMssBean.setTouser(toUserOpenId);
		wxMssBean.setPage("pages/user/order-details/index?id="+userOrderInfo.getOid());
		wxMssBean.setTemplate_id("fWMHzkV10pTOxjLkQcdQI6CBQ3ReR0UqIYqj04SPPNI");
		Map<String, TemplateData> data = new HashedMap(5, 1);

		TemplateData keyword1 = new TemplateData();
		keyword1.setValue(userOrderInfo.getOno());
		data.put("keyword1", keyword1);

		TemplateData keyword2 = new TemplateData();
		keyword2.setValue(userOrderInfo.getPayPrice().toString());
		data.put("keyword2", keyword2);

		TemplateData keyword3 = new TemplateData();
		keyword3.setValue(DateUtil.dateToString(new Date(userOrderInfo.getPayTime().getTime()), DateUtil.YYYYMMDDHHMMSS));
		data.put("keyword3", keyword3);

		List<UserOrderDetail> userOrderDetails = userOrderInfo.getUserOrderDetails();
		StringBuilder keyword4Value = new StringBuilder();
		for(UserOrderDetail userOrderDetail : userOrderDetails){
			if(keyword4Value.length() > 0) keyword4Value.append("、");
			keyword4Value.append(userOrderDetail.getItemName());
		}

		TemplateData keyword4 = new TemplateData();
		keyword4.setValue(keyword4Value.toString());
		data.put("keyword4", keyword4);

		wxMssBean.setData(data);

		return HttpConnect.httpsRequest(XcxConstant.TPL_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken.getToken()), "POST", JsonUtils.toJson(wxMssBean));
	}

	@Desc("模板ID\n" +
			"bHWxbxTtM8E1D8uxwcDkmH2j44Iy6aVQ_PztH53KpSc\n" +
			"标题\n" +
			"充值成功通知\n" +
			"关键词\n" +
			"商家名称\n" +
			"{{keyword1.DATA}}\n" +
			"充值时间\n" +
			"{{keyword2.DATA}}\n" +
			"充值金额\n" +
			"{{keyword3.DATA}}\n" +
			"赠送金额\n" +
			"{{keyword4.DATA}}\n" +
			"账户余额\n" +
			"{{keyword5.DATA}}")
	public static JSONObject sendOrderCZSuccess(String appid, String appsecret, UserOrderInfo userOrderInfo, String toUserOpenId, UserAccount userAccount){
		AccessToken accessToken = getAccessToken(appid, appsecret);
		WxMssBean wxMssBean = new WxMssBean();
		wxMssBean.setForm_id(userOrderInfo.getPrepayId());
		wxMssBean.setTouser(toUserOpenId);
		wxMssBean.setPage("pages/user/transactions/index");
		wxMssBean.setTemplate_id("bHWxbxTtM8E1D8uxwcDkmH2j44Iy6aVQ_PztH53KpSc");
		Map<String, TemplateData> data = new HashedMap(6, 1);

		TemplateData keyword1 = new TemplateData();
		keyword1.setValue("衣否");
		data.put("keyword1", keyword1);

		TemplateData keyword2 = new TemplateData();
		keyword2.setValue(DateUtil.dateToString(new Date(userOrderInfo.getPayTime().getTime()), DateUtil.YYYYMMDDHHMMSS));
		data.put("keyword2", keyword2);

		List<UserOrderDetail> userOrderDetails1 = userOrderInfo.getUserOrderDetails();
		TemplateData keyword3 = new TemplateData();
		keyword3.setValue(userOrderDetails1.get(0).getItemPriceSum().toString());
		data.put("keyword3", keyword3);

		TemplateData keyword4 = new TemplateData();
		keyword4.setValue(userOrderDetails1.size() > 1 ? userOrderDetails1.get(1).getItemPriceSum().toString() : "0");
		data.put("keyword4", keyword4);


		TemplateData keyword5 = new TemplateData();
		keyword5.setValue(StrUtil.objToStr(StrUtil.roundKeepTwo(userAccount.getBalance().doubleValue())));
		data.put("keyword5", keyword5);

		wxMssBean.setData(data);

		return HttpConnect.httpsRequest(XcxConstant.TPL_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken.getToken()), "POST", JsonUtils.toJson(wxMssBean));
	}

	@Desc("MwOUnIoFVmHCv4wr14GUvPyGHptPltFvYBNFpLMV8Uw\n" +
			"标题\n" +
			"订单发货提醒\n" +
			"关键词\n" +
			"订单号\n" +
			"{{keyword1.DATA}}\n" +
			"物品名称\n" +
			"{{keyword2.DATA}}\n" +
			"发货时间\n" +
			"{{keyword3.DATA}}\n" +
			"快递公司\n" +
			"{{keyword4.DATA}}")
	public static JSONObject sendOrderFHSuccess(String appid, String appsecret, UserOrderInfo userOrderInfo, String toUserOpenId){
		AccessToken accessToken = getAccessToken(appid, appsecret);
		WxMssBean wxMssBean = new WxMssBean();
		wxMssBean.setForm_id(userOrderInfo.getPrepayId());
		wxMssBean.setTouser(toUserOpenId);
		wxMssBean.setPage("pages/user/order-details/index?id="+userOrderInfo.getOid());
		wxMssBean.setTemplate_id("MwOUnIoFVmHCv4wr14GUvPyGHptPltFvYBNFpLMV8Uw");
		Map<String, TemplateData> data = new HashedMap(5, 1);

		TemplateData keyword1 = new TemplateData();
		keyword1.setValue(userOrderInfo.getOno());
		data.put("keyword1", keyword1);

		List<UserOrderDetail> userOrderDetails = userOrderInfo.getUserOrderDetails();
		StringBuilder keyword4Value = new StringBuilder();
		for(UserOrderDetail userOrderDetail : userOrderDetails){
			if(keyword4Value.length() > 0) keyword4Value.append("、");
			keyword4Value.append(userOrderDetail.getItemName());
		}

		TemplateData keyword2 = new TemplateData();
		keyword2.setValue(keyword4Value.toString());
		data.put("keyword2", keyword2);

		TemplateData keyword3 = new TemplateData();
		keyword3.setValue(DateUtil.dateToString(new Date(userOrderInfo.getExpressTime().getTime()), DateUtil.YYYYMMDDHHMMSS));
		data.put("keyword3", keyword3);

		TemplateData keyword4 = new TemplateData();
		keyword4.setValue(userOrderInfo.getExpressCompany());
		data.put("keyword4", keyword4);

		wxMssBean.setData(data);

		return HttpConnect.httpsRequest(XcxConstant.TPL_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken.getToken()), "POST", JsonUtils.toJson(wxMssBean));
	}
	
	/**
	 * 获取网页授权凭证
	 * 
	 * @param appId
	 *            公众账号的唯一标识
	 * @param appSecret
	 *            公众账号的密钥
	 * @param code
	 * @return WeixinAouth2Token
	 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// 获取网页授权凭证
		JSONObject jsonObject = JSONObject.fromObject(HttpConnect.httpsRequest(requestUrl, "GET", null));
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.error("获取网页授权凭证失败 errcode:"+errorCode+"，"+errorMsg);
			}
		}
		return wat;
	}

	public static String getKey() {
		return KEY;
	}

	public static String getSecret() {
		return SECRET;
	}
	
}
