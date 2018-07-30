package com.wardrobe.controller;

import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.bean.UserPerfectBean;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.controller.annotation.UserPerfect;
import com.wardrobe.platform.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("user")
@Controller
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("updateUser")
    public ResponseBean updateUser(@UserPerfect UserPerfectBean userPerfectBean, String code){
        Object yzm = super.getRequest().getSession().getAttribute(IPlatformConstant.PERFECT_MOBILE_CAPTCHA);
        if(yzm == null || !(userPerfectBean.getMobile() + IPlatformConstant.AND + code).equals(yzm)) throw new MessageException("验证码错误");
        userPerfectBean.setUnionId(super.getUserUnionId());
        userService.updateUser(userPerfectBean);
        return new ResponseBean(true);
    }

}
