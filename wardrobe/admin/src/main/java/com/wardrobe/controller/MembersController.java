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

    @Desc("会员编辑")
    @NotProtected
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView renderMembersEdit(String memberId) {
        ModelAndView modelAndView = new ModelAndView("Members/Edit");

        return setModelAndView(modelAndView);
    }

    @Desc("会员添加")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView renderMembersAdd() {

        ModelAndView modelAndView = new ModelAndView("Members/Add");

        return setModelAndView(modelAndView);
    }

    @Desc("保存会员信息")
    @ResponseBody
    @RequestMapping(value = "/saveMemberInfo", method = RequestMethod.POST)
    public ResponseBean saveMemberInfo() {
        try {
            return new ResponseBean(true);
        } catch (MessageException e) {
            e.printStackTrace();
            return new ResponseBean(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(false);
        }
    }

}
