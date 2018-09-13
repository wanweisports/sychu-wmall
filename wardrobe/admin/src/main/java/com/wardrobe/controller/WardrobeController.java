package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.annotation.NotProtected;
import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.po.SysDict;
import com.wardrobe.common.po.UserInfo;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.UserInputView;
import com.wardrobe.common.view.UserTransactionsInputView;
import com.wardrobe.platform.service.IDictService;
import com.wardrobe.platform.service.IUserService;
import com.wardrobe.platform.service.IUserTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 试衣间管理
 */
@Controller
@RequestMapping("/admin/wardrobe")
public class WardrobeController extends BaseController {

    private ModelAndView setModelAndView(ModelAndView modelAndView) {
        return modelAndView.addObject("Admin", super.getRequest().getSession().getAttribute(IPlatformConstant.LOGIN_USER));
    }

    @Desc("试衣间列表")
    @NotProtected
    @RequestMapping(value = "/list")
    public String renderWardrobeList(UserInputView userInputView, Model model) {
        return "Wardrobe/List";
    }

    @Desc("试衣间面板")
    @NotProtected
    @RequestMapping(value = "/dashboard")
    public String renderWardrobeDashboard(UserInputView userInputView, Model model) {
        return "Wardrobe/Dashboard";
    }

    @Desc("试衣间配置")
    @NotProtected
    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String renderWardrobeSettings(int userId, Model model) {
        return "Wardrobe/Settings";
    }

    @Desc("试衣间运行日志")
    @NotProtected
    @RequestMapping(value = "/running/log", method = RequestMethod.GET)
    public String renderWardrobeRunningLog(int userId, Model model) {
        return "Wardrobe/Log";
    }
}
