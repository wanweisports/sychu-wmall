package com.wardrobe.controller;

import com.wardrobe.common.annotation.NotProtected;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.OperatorInfo;
import com.wardrobe.platform.service.IOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cxs on 2018/10/22.
 */
@RequestMapping("operator")
@Controller
public class OperatorController extends BaseController {

    @Autowired
    private IOperatorService operatorService;

    @NotProtected
    @RequestMapping("login")
    public String login(){
        return "Passport/Login";
    }

    @NotProtected
    @ResponseBody
    @RequestMapping("loginIn")
    public ResponseBean loginIn(OperatorInfo operatorInfo){
        OperatorInfo op = operatorService.loginIn(operatorInfo);
        if(op == null) throw new MessageException("用户名或密码错误！");
        getRequest().getSession().setAttribute(IPlatformConstant.LOGIN_USER_IN, op);
        return new ResponseBean(true);
    }

    // 用户退出
    @RequestMapping("loginLogout")
    public String userLogout() {
        super.getRequest().getSession().invalidate();
        return redirect("/operator/login");
    }

}
