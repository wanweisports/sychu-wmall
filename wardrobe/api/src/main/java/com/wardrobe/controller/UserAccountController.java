package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.bean.UserPerfectBean;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.enum_.MobileMessageEnum;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.UserAccount;
import com.wardrobe.controller.annotation.UserPerfect;
import com.wardrobe.platform.service.IUserAccountService;
import com.wardrobe.platform.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@RequestMapping("account")
@Controller
public class UserAccountController extends BaseController {

    @Autowired
    private IUserAccountService userAccountService;

    @Desc("获取用户账户余额")
    @ResponseBody
    @RequestMapping("userAccountBalance")
    public ResponseBean userAccountBalance(){
        return new ResponseBean(new HashMap(1,1){{put("balance", userAccountService.getUserAccountBalance(getUserInfo().getUid()));}});
    }

    @Desc("获取用户积分")
    @ResponseBody
    @RequestMapping("userScore")
    public ResponseBean userScore(){
        return new ResponseBean(userAccountService.getScore(getUserInfo().getUid()));
    }

}











