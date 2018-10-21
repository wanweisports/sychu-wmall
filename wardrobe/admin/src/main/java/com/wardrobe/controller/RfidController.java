package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.exception.MessageException;
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
        rfidService.connectRfid(new RfidBean(rfidInfo.getIp(), rfidInfo.getPort(), rfidInfo.getWorkAntenna()));
        return new ResponseBean(true);
    }

    @Desc("断开射频")
    @ResponseBody
    @RequestMapping(value = "/closeRfid")
    public ResponseBean closeRfid(int rfid){
        SysRfidInfo rfidInfo = rfidService.getRfidInfo(rfid);
        rfidService.closeRfid(new RfidBean(rfidInfo.getIp(), rfidInfo.getPort(), rfidInfo.getWorkAntenna()));
        return new ResponseBean(true);
    }

    @Desc("测试读取射频电子标签")
    @ResponseBody
    @RequestMapping(value = "/readEpcLabelCK")
    public ResponseBean readEpcLabelCK(int did){
        SysRfidInfo rfidInfo = rfidService.getSysRfidInfoByDid(did, IDBConstant.LOGIC_STATUS_YES);
        return new ResponseBean(rfidService.readEpcLabelIn(new RfidBean(rfidInfo.getIp(), rfidInfo.getPort(), rfidInfo.getWorkAntenna()), 10));
    }

    @Desc("读取商场射频电子标签")
    @ResponseBody
    @RequestMapping(value = "/readEpcLabelSC")
    public ResponseBean readEpcLabelSC(int did){
        try {
            SysRfidInfo rfidInfo = rfidService.getSysRfidInfoByDid(did, IDBConstant.LOGIC_STATUS_YES);
            return new ResponseBean(rfidService.readEpcLabelApi(new RfidBean(rfidInfo.getIp(), rfidInfo.getPort(), rfidInfo.getWorkAntenna()), 10, did));
        }catch (MessageException e){ //由于是api请求过来的接口，admin处理未按ajax处理错误，这里用try一下
            return new ResponseBean(e.getMessage());
        }
    }

}
