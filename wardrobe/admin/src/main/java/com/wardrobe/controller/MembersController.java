package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.annotation.NotProtected;
import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.SysDict;
import com.wardrobe.common.po.UserInfo;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.UserInputView;
import com.wardrobe.common.view.UserTransactionsInputView;
import com.wardrobe.platform.service.IDictService;
import com.wardrobe.platform.service.IUserService;
import com.wardrobe.platform.service.IUserTransactionsService;
import org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 会员管理
 */
@Controller
@RequestMapping("/admin/members")
public class MembersController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserTransactionsService userTransactionsService;

    @Autowired
    private IDictService dictService;

    private ModelAndView setModelAndView(ModelAndView modelAndView) {
        return modelAndView.addObject("Admin", super.getRequest().getSession().getAttribute(IPlatformConstant.LOGIN_USER));
    }

    @Desc("会员列表")
    @NotProtected
    @RequestMapping(value = "/list")
    public String renderMembersList(UserInputView userInputView, Model model) {
        Map<String, Object> params = JsonUtils.fromJsonDF(userInputView);
        model.addAllAttributes(params);
        PageBean pageBean = userService.getUserListIn(userInputView);
        super.setPageInfo(model, pageBean, "/admin/members/list", params);
        return "Members/List";
    }

    @Desc("会员详情")
    @NotProtected
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String renderMembersDetail(int userId, Model model) {
        model.addAllAttributes(userService.getMembersDetailIn(userId));
        return "Members/Detail";
    }

    @Desc("会员停用")
    @ResponseBody
    @NotProtected
    @RequestMapping(value = "/setCloseStatus", method = RequestMethod.POST)
    public ResponseBean setCloseStatus(int memberId) {
        return new ResponseBean(true);
    }

    @Desc("会员交易流水")
    @NotProtected
    @RequestMapping(value = "/transactions/log")
    public String renderMembersTransactionsLog(UserTransactionsInputView userTransactionsInputView, Model model) {
        model.addAllAttributes(JsonUtils.fromJsonDF(userTransactionsInputView));
        Integer uid = userTransactionsInputView.getUid();
        if(uid != null){
            UserInfo userInfo = userService.getUserInfo(uid);
            model.addAttribute("nickname", userInfo.getNickname());
            model.addAttribute("mobile", userInfo.getMobile());
        }
        userTransactionsInputView.setIsWxType(true);
        super.setPageInfo(model, userTransactionsService.getUserTransactionsListIn(userTransactionsInputView), "/admin/members/transactions/log", userTransactionsInputView);
        model.addAllAttributes(userTransactionsService.countTransactions(userTransactionsInputView));
        return "Members/TransactionsLog";
    }

    @Desc("会员充值设置")
    @NotProtected
    @RequestMapping(value = "/recharge/settings")
    public String renderMembersRechargeSettings(Model model) {
        model.addAttribute("dicts", dictService.getDicts(IDBConstant.RECHARGE_TYPE));
        return "Members/RechargeSettings";
    }

    @Desc("添加充值")
    @ResponseBody
    @RequestMapping(value = "/recharge/addRecharge")
    public ResponseBean addRecharge(SysDict sysDict){
        sysDict.setDictName(IDBConstant.RECHARGE_TYPE);
        sysDict.setDictKey(StrUtil.EMPTY);
        dictService.addDict(sysDict);

        return new ResponseBean(true);
    }

    @Desc("删除充值")
    @ResponseBody
    @RequestMapping(value = "/recharge/deleteRecharge")
    public ResponseBean deleteRecharge(int dictId) {
        dictService.deleteDict(dictId);
        return new ResponseBean(true);
    }

}
