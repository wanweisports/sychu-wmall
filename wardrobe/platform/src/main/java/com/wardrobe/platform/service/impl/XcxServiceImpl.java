package com.wardrobe.platform.service.impl;

import com.wardrobe.common.constant.XcxConstant;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.service.IOperatorService;
import com.wardrobe.platform.service.IXcxService;
import com.wardrobe.wx.util.WXAppletUserInfo;
import com.wardrobe.wx.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XcxServiceImpl extends BaseService implements IXcxService {

    @Autowired
    private IOperatorService operatorService;

    @Override
    public JSONObject xcxLogn(String session_key, String code, String xcxAppId, String xcxAppsecret, String iv, String encryptedData){

        JSONObject userInfo = null;
        System.out.println(session_key);
        if(session_key == null) { //4D8184E7B1C8A744895F9E1C88C74B71
            String xcxLoginUrl = XcxConstant.LOGIN_URL.replace("APPID", xcxAppId).replace("SECRET", xcxAppsecret).replace("JSCODE", code);
            JSONObject jsonObject = WeixinUtil.doGetStr(xcxLoginUrl);
            System.out.println(jsonObject);
            session_key = jsonObject.getString("session_key"); //先查找session有无缓存session_key
        }
        if(StrUtil.isNotBlank(session_key)) {
            userInfo = WXAppletUserInfo.getUserInfo(encryptedData, session_key, iv);
            userInfo.put("session_key", session_key);
        }
        /*code:011eiHwj0BkZmn1FDExj0RTXwj0eiHwZ
        iv:uycq/rRLyD5ooaMsQPIhrA==
                encryptedData:O4lTV4n2LKyI4DG0nkG819iC7fjL8kst7YFsWFMR855cN3RrXBg0I4KZjxWKChgOYXJNh8lFPAO8SiGmYhHN+qtx+wbN157IAnatpOASOFfVC5xWo/j+N4l52UD3JLVSfTGnCYCMOZJV0U7GrDf3NxYosqde4ThHS0goIoyLbJZQ5hMi6iBPJouCp55HcYNjwtlg9sgWSPVOXn62UtGg/UCbB53wtAiniJtfn7rIMqgoiN2si8lPhtElabV58laP9g5FFqheHMGB0CpFWrWZGn+LDJoCHeNXCO60GkSPUSojkRTKiEXKIwZIqN5oe/QWEZxPedBbBEWcqPzqp7vjNvfSxg396QiFgSlYBEfweiLVShyO4u7UuHeZnxdJ3i9FfksEQM0QQvhazSvZoGcuQRyA10sqS2ur4XMQmYFCCK/Dl1L1CaELkDDa4Qt2IUjMmZOmPnkpZyxH9znMlUrJWytN/bI/hKUYBNc+T5C0YlE=
                011eiHwj0BkZmn1FDExj0RTXwj0eiHwZ
        {"session_key":"bqiVGAnqlS/I9HC/+AxbTQ==","openid":"o0Dj10J1PA96qthKgNA3_IruBJ-Y"}*/

        //{"openId":"o0Dj10J1PA96qthKgNA3_IruBJ-Y","nickName":"Echo????","gender":1,"language":"zh_CN","city":"Chaoyang","province":"Beijing","country":"China","avatarUrl":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLWWlU9dp0nOxtyhLGD5OfRgLiaITQW8iayMcTKicdtyXWcLy1Ns5VH5iavefvPgYQAdLv2maib1EdoiaKg/0","unionId":"olTOa0feeB2tVuk8pKjfh2c9yH8c","watermark":{"timestamp":1512555016,"appid":"wx14967e4aaa8cb46f"},"session_key":"3tOWAoOE3xXnKOJdB9kqmw=="}

        String unionId = userInfo.getString("unionId");
        /*Operator operator = operatorService.getOperatorByUnionId(unionId);
        if(operator == null){
            SNSUserInfo snsUserInfo = new SNSUserInfo();
            snsUserInfo.setOpenId(userInfo.getString("openId"));
            snsUserInfo.setNickname(userInfo.getString("nickName"));
            snsUserInfo.setHeadImgUrl(userInfo.getString("avatarUrl"));
            snsUserInfo.setUnionId(unionId);
            operatorService.saveShareOperator(null, snsUserInfo);
        }*/
        return userInfo;
    }

}
