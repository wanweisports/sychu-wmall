package com.wardrobe.controller;

import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.enum_.MobileMessageEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Created by cxs on 2018/7/30.
 */
@RequestMapping("message")
@Controller
public class MessageController extends BaseController {

    @ResponseBody
    @RequestMapping("getCode")
    public ResponseBean getMessage(int type, String mobile){
        System.out.println("type:" + type);

        MobileMessageEnum messageEnum = MobileMessageEnum.getMessageByType(type);
        int code = 1000 + new Random().nextInt(8999);
        System.out.println(mobile + "下发短信验证码：" + code);
        super.getRequest().getSession().setAttribute(messageEnum.name, mobile + IPlatformConstant.AND + code);

        return new ResponseBean(true);
    }

}
