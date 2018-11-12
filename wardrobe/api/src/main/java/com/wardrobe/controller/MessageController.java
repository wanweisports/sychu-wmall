package com.wardrobe.controller;

import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.enum_.MobileMessageEnum;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.sms.SmsYunPianApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by cxs on 2018/7/30.
 */
@RequestMapping("message")
@Controller
public class MessageController extends BaseController {

    @ResponseBody
    @RequestMapping("getCode")
    public ResponseBean getMessage(int type, String mobile) throws UnsupportedEncodingException{
        System.out.println("type:" + type);
        MobileMessageEnum messageEnum = MobileMessageEnum.getMessageByType(type);
        int code = StrUtil.initCode(4);
        System.out.println(mobile + "下发短信验证码：" + code);

        //if(1!=1) { //正式
        String sendJson = SmsYunPianApi.sendCode(StrUtil.objToStr(code), mobile, messageEnum.tplId);//发送短信

        if (sendJson != null) {
            Map<String, Object> sendMap = JsonUtils.fromJsonDF(sendJson, Map.class);  //解析发送短信json数据
            if (StrUtil.objToInt(sendMap.get("code")) == 0) {               //发送短信返回成功
                super.getRequest().getSession().setAttribute(messageEnum.name, mobile + IPlatformConstant.AND + code); //发送成功后，存储到session
                return new ResponseBean(true);
            }
        }
        return new ResponseBean(false, "发送失败，请稍候再试!");
        /*}else{ //测试
            super.getRequest().getSession().setAttribute(messageEnum.name, mobile + IPlatformConstant.AND + code); //发送成功后，存储到session
            return new ResponseBean(true);
        }*/
    }

}
