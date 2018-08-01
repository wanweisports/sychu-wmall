package com.wardrobe.controller;

import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.bean.UserPerfectBean;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.enum_.MobileMessageEnum;
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

    /*
     * 用户完善资料
     */
    @ResponseBody
    @RequestMapping("updateUser")
    public ResponseBean updateUser(@UserPerfect UserPerfectBean userPerfectBean, String code){
        Object yzm = super.getRequest().getSession().getAttribute(MobileMessageEnum.USER_PERFECT.name);
        if(yzm == null || !(userPerfectBean.getMobile() + IPlatformConstant.AND + code).equals(yzm)) throw new MessageException("验证码错误");
        userPerfectBean.setUserId(super.getUserId());
        userService.updateUser(userPerfectBean);
        return new ResponseBean(true);
    }

    /*
     * 邀请人是否存在，并不能是自己
     */
    @ResponseBody
    @RequestMapping("checkInviteCode")
    public ResponseBean checkInviteCode(String inviteCode){
        userService.checkInviteCode(inviteCode, getUserId());
        return new ResponseBean(true);
    }

    /*
     * 个人中心
     */
    @ResponseBody
    @RequestMapping("getUserCenter")
    public ResponseBean getUserCenter(){
        return new ResponseBean(userService.getUserCenter(getUserId()));
    }

    /*
     * 个人设置
     */
    @ResponseBody
    @RequestMapping("getUserSetting")
    public ResponseBean getUserSetting(){
        return new ResponseBean(userService.getUserSetting(getUserId()));
    }

    /*
     * 修改手机号，验证第一步原手机号
     */
    @ResponseBody
    @RequestMapping("checkOldMobile")
    public ResponseBean checkOldMobile(String mobile, String code){
        return new ResponseBean(super.checkMobileMessage(MobileMessageEnum.USER_UPDATE_MOBILE_OLD, mobile, code));
    }

    /*
     * 修改手机号，验证及修改第二步新手机号
     */
    @ResponseBody
    @RequestMapping("updateNewMobile")
    public ResponseBean updateNewMobile(String mobile, String code){
        if(!super.checkMobileMessage(MobileMessageEnum.USER_UPDATE_MOBILE_NEW, mobile, code)) throw new MessageException("验证码不正确，请重新输入");

        userService.updateUserMobile(getUserId(), mobile);
        return new ResponseBean(true);
    }

}











