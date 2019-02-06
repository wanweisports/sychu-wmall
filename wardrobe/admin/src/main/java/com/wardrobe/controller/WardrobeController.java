package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.po.SysDeviceInfo;
import com.wardrobe.common.view.DeviceInputView;
import com.wardrobe.platform.service.IRelayService;
import com.wardrobe.platform.service.IRfidService;
import com.wardrobe.platform.service.ISysDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 试衣间管理
 */
@Controller
@RequestMapping("/admin/wardrobe")
public class WardrobeController extends BaseController {

    @Autowired
    private ISysDeviceService deviceService;

    @Autowired
    private IRelayService relayService;

    @Autowired
    private IRfidService rfidService;

    @ResponseBody
    @RequestMapping("saveDeviceInfo")
    public ResponseBean saveDeviceInfo(SysDeviceInfo sysDeviceInfo){
        deviceService.saveSysDeviceInfo(sysDeviceInfo);
        return new ResponseBean(true);
    }

    @Desc("试衣间列表")
    @RequestMapping(value = "/list")
    public String renderWardrobeList(DeviceInputView deviceInputView, Model model) {
        PageBean pageBean = deviceService.getSysDeviceInfoList(deviceInputView);
        setPageInfo(model, pageBean, "/admin/wardrobe/list", deviceInputView);
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
    public String renderWardrobeDashboard(int did, Model model) {
        model.addAllAttributes(relayService.readAll(did));
        /*model.addAllAttributes(rfidService.getSysRfidIndexsIn());*/
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
