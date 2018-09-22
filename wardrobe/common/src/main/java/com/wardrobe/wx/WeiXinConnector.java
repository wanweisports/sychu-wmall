package com.wardrobe.wx;

import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.wx.http.HttpConnect;
import com.wardrobe.wx.pojo.AccessToken;
import com.wardrobe.wx.pojo.WeixinOauth2Token;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;


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
	public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	
	public static String accessToken = null;
	
	public static Long lastGetAccessTokenTime = null;
	
	/**
	 * 接口Token
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
    	AccessToken accessToken = null;

    	String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
    	JSONObject jsonObject = HttpConnect.httpsRequest(requestUrl, "GET", null);
    	// 如果请求成功
    	if (null != jsonObject) {
    		try {
    			accessToken = new AccessToken();
    			accessToken.setToken(jsonObject.getString("access_token"));
    			accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
    		} catch (JSONException e) {
    			accessToken = null;
    			// 获取token失败
    			e.printStackTrace();
    			//log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
    		}
    	}
    	return accessToken;
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
