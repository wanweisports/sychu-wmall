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

    @Desc("试衣间列表")
    @RequestMapping(value = "/list")
    public String renderWardrobeList() {
        return "Wardrobe/List";
    }

    @Desc("获取试衣间信息")
    @ResponseBody
    @RequestMapping("/getInfo")
    public ResponseBean saveWardrobeInfo(int wardrobeId) {
        return new ResponseBean(true);
    }

    @Desc("试衣间保存")
    @ResponseBody
    @RequestMapping("/saveInfo")
    public ResponseBean saveWardrobeInfo() {
        return new ResponseBean(true);
    }

    @Desc("试衣间面板")
    @RequestMapping(value = "/dashboard")
    public String renderWardrobeDashboard() {
        return "Wardrobe/Dashboard";
    }

    @Desc("试衣间连接")
    @ResponseBody
    @RequestMapping("/connect")
    public ResponseBean connectWardrobe() {
        return new ResponseBean(true);
    }

    @Desc("大门开锁关锁")
    @ResponseBody
    @RequestMapping("/door/lock")
    public ResponseBean lockWardrobeDoor() {
        return new ResponseBean(true);
    }

    @Desc("柜子开锁关锁")
    @ResponseBody
    @RequestMapping("/cabinet/lock")
    public ResponseBean lockWardrobeCabinet() {
        return new ResponseBean(true);
    }

    @Desc("试衣间运行日志")
    @RequestMapping(value = "/running/log")
    public String renderWardrobeRunningLog() {
        return "Wardrobe/Log";
    }
}
