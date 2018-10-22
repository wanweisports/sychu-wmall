package com.wardrobe.controller;

import com.wardrobe.common.annotation.NotProtected;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.UserOperator;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.service.IOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("mobile")
public class MobileController extends BaseController {

    @Autowired
    private IOperatorService operatorService;

    // 用户登录
    @NotProtected
    @ResponseBody
    @RequestMapping("/passport/userLogin")
    public ResponseBean userLogin(String mobile, String password) {
        try{
            if(StrUtil.isBlank(mobile)) throw new MessageException("请输入手机号！");
            if(StrUtil.isBlank(password)) throw new MessageException("请输入密码！");

            /*UserOperator operator = null; //operatorService.userMobileLogin(mobile);
            if(operator == null) throw new MessageException("此用户不存在！");
            if(!password.equals(operator.getOperatorPwd())) throw new MessageException("密码错误！");
            if(!IDBConstant.LOGIC_STATUS_YES.equals(operator.getStatus())) throw new MessageException("您的帐号已被锁定，请联系管理员！");
            operator.setOperatorPwd(null);

            //operatorService.saveLastLoginTime(operator.getId());
            super.getRequest().getSession().setAttribute(IPlatformConstant.LOGIN_USER, operator);*/
            //return new ResponseBean(true);
            return new ResponseBean(/*new HashMap(){{put("users", operatorService.get());}}*/true);
        } catch (MessageException e) {
            e.printStackTrace();
            return new ResponseBean(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(false);
        }
    }

    /*// 用户退出
    @ResponseBody
    @RequestMapping("passport/userLogout")
    public ResponseBean userLogout() {
        try{
            super.getRequest().getSession().invalidate();
            return new ResponseBean(true);
        } catch (MessageException e) {
            e.printStackTrace();
            return new ResponseBean(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(false);
        }
    }*/

}
