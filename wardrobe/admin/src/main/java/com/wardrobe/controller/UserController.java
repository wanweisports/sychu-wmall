package com.wardrobe.controller;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.view.UserInputView;
import com.wardrobe.platform.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cxs on 2018/8/7.
 */
@RequestMapping("user")
@Controller
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping("getUsers")
    public String getUsers(UserInputView userInputView, Model model){
        PageBean pageBean = userService.getUserListIn(userInputView);
        setPageInfo(model, pageBean);
        return "";
    }

}
