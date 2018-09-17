package com.wardrobe.sms;

import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.constant.SmsUrlConstant;
import com.wardrobe.common.util.HttpUtil;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.common.util.StrUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 云片短信接口
 */
public class SmsYunPianApi implements SmsUrlConstant {

    private final static String API_KEY = "eda9b0a2e7dfc6aa6b9c51b428972a48";

    public final static String ENCODE = "UTF-8";

    /**
     * 模板短信发送
     * 返回示例：
     * PS：验证码类短信1小时内同一手机号发送次数不能超过3次，模版单发接口会报错，群发接口会返回错误信息，这里是有单发接口
     */
    public static String sendCode(String tplValue, String mobile, String tplId) throws UnsupportedEncodingException{
        Map<String, String> params = new HashMap<>(4, 1);
        params.put("apikey", API_KEY);
        params.put("tpl_id", tplId); //模版id
        params.put("tpl_value", URLEncoder.encode(new StringBuilder(URLEncoder.encode("#code#", ENCODE)).append("=").append(URLEncoder.encode(tplValue, ENCODE)).toString(), ENCODE));
        params.put("mobile", mobile);
        try{
            return HttpUtil.sendPost(TPL_SINGLE_SEND, params);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception{
        try{
            /*String json = sendCode("1123", "15110275787", TPL_ID_CODE);
            System.out.println(json);*/
            Map<String, Object> sendMap = JsonUtils.fromJsonDF("{\"code\":0,\"msg\":\"发送成功\",\"count\":1,\"fee\":0.05,\"unit\":\"RMB\",\"mobile\":\"15110275787\",\"sid\":28243995676}", Map.class);  //解析发送短信json数据
            if (StrUtil.objToInt(sendMap.get("code")) == 0) {               //发送短信返回成功
                System.out.println(1);
            }else{
                System.out.println(2);
                StrUtil.objToInt(sendMap.get("code"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}










