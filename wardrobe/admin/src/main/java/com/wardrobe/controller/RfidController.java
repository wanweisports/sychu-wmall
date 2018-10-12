package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.po.SysRfidInfo;
import com.wardrobe.platform.rfid.bean.RfidBean;
import com.wardrobe.platform.service.IRfidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cxs on 2018/10/12.
 */
@RequestMapping("rfid")
@Controller
public class RfidController extends BaseController {

    @Autowired
    private IRfidService rfidService;

    private int rfid1 = 1;
    private int rfid2 = 2;

    @Desc("连接射频")
    @ResponseBody
    @RequestMapping(value = "/connectRfid")
    public ResponseBean connectRfid(int rfid){
        SysRfidInfo rfidInfo = rfidService.getRfidInfo(rfid);
        rfidService.connectRfid(new RfidBean(rfidInfo.getIp(), rfidInfo.getPort()));
        return new ResponseBean(true);
    }

    @Desc("断开射频")
    @ResponseBody
    @RequestMapping(value = "/closeRfid")
    public ResponseBean closeRfid(int rfid){
        SysRfidInfo rfidInfo = rfidService.getRfidInfo(rfid);
        rfidService.closeRfid(new RfidBean(rfidInfo.getIp(), rfidInfo.getPort()));
        return new ResponseBean(true);
    }

    @Desc("读取电子标签")
    @ResponseBody
    @RequestMapping(value = "/readEpcLabel")
    public ResponseBean readEpcLabel(int rfid, int count){
        SysRfidInfo rfidInfo = rfidService.getRfidInfo(rfid);
        rfidService.readEpcLabel(new RfidBean(rfidInfo.getIp(), rfidInfo.getPort()), count);
        return new ResponseBean(true);
    }

}
