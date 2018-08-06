package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.constant.IPlatformConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wangjun on 2017/4/25.
 */
@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController extends BaseController {

    private ModelAndView setModelAndView(ModelAndView modelAndView) {
        return modelAndView.addObject("Admin", super.getRequest().getSession().getAttribute(IPlatformConstant.LOGIN_USER));
    }

    @Desc("工作面板")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView renderDashboardIndex() {
        ModelAndView modelAndView = new ModelAndView("Dashboard/Index");

        return setModelAndView(modelAndView);
    }
}
