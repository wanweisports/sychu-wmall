package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.annotation.NotProtected;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.exception.MessageException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 会员管理
 */
@Controller
@RequestMapping("/admin/members")
public class MembersController extends BaseController {

    private ModelAndView setModelAndView(ModelAndView modelAndView) {
        return modelAndView.addObject("Admin", super.getRequest().getSession().getAttribute(IPlatformConstant.LOGIN_USER));
    }

    @Desc("会员列表")
    @NotProtected
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView renderMembersList() {
        ModelAndView modelAndView = new ModelAndView("Members/List");

        return setModelAndView(modelAndView);
    }

    @Desc("会员详情")
    @NotProtected
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView renderMembersDetail() {
        ModelAndView modelAndView = new ModelAndView("Members/Detail");

        return setModelAndView(modelAndView);
    }

    @Desc("会员充值流水")
    @NotProtected
    @RequestMapping(value = "/transactions/log", method = RequestMethod.GET)
    public ModelAndView renderMembersTransactionsLog() {
        ModelAndView modelAndView = new ModelAndView("Members/TransactionsLog");

        return setModelAndView(modelAndView);
    }

    @Desc("会员充值设置")
    @NotProtected
    @RequestMapping(value = "/recharge/settings", method = RequestMethod.GET)
    public ModelAndView renderMembersRechargeSettings() {
        ModelAndView modelAndView = new ModelAndView("Members/RechargeSettings");

        return setModelAndView(modelAndView);
    }

}
