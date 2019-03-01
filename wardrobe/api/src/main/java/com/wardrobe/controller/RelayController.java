package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.annotation.Perfect;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.po.SysDeviceControl;
import com.wardrobe.common.util.HttpUtil;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.platform.service.IRelayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by cxs on 2018/9/10.
 */
@Controller
@RequestMapping("relay")
public class RelayController extends BaseController {

    private Logger logger = Logger.getLogger(RelayController.class);

    @Autowired
    private IRelayService relayService;

    @Desc("开启门")
    @Perfect //必须完善资料
    @ResponseBody
    @RequestMapping("openDoor")
    public ResponseBean openDoor(int did) throws Exception{
        String response = HttpUtil.sendGet(relayUrl + "/userOpenDrive?did="+did); //判断柜子版
        //String response = HttpUtil.sendGet(relayUrl + "/openDrive?did="+did);  //直接开门版
        Map map = JsonUtils.fromJson(response, Map.class);
        return new ResponseBean(map.get("code").toString(), map.get("message").toString());
    }

    @Desc("关门")
    @ResponseBody
    @RequestMapping("closeDoor")
    public ResponseBean closeDoor(int did) throws Exception{
        logger.info("closeDoor===did:" + did);
        String response = HttpUtil.sendGet(relayUrl + "/userCloseDrive?did=1"); //判断柜子版
        //String response = HttpUtil.sendGet(relayUrl + "/closeDrive?did="+did); //直接关门版
        Map map = JsonUtils.fromJson(response, Map.class);
        return new ResponseBean(map.get("code").toString(), map.get("message").toString());
    }

    @Desc("开启锁")
    @ResponseBody
    @RequestMapping("openLock")
    public ResponseBean openLock(int lockId) throws Exception{
        /*String response = HttpUtil.sendGet(relayUrl + "/userOpenLock?driveId="+lockId);*/
        System.out.println("sys-openLock lockId：" + lockId);
        logger.info("logger.info---openLock lockId：" + lockId);

        SysDeviceControl sysDeviceControl = relayService.getSysDeviceControl(lockId);
        /*String response = HttpUtil.sendGet(relayUrl + "/openLock?did=" + sysDeviceControl.getDid() + "&lockId=" + sysDeviceControl.getLockId());*/
        String response = HttpUtil.sendGet(relayUrl + "/userOpenLock?did=" + sysDeviceControl.getDid() + "&lockId=" + sysDeviceControl.getLockId());
        Map map = JsonUtils.fromJson(response, Map.class);
        return new ResponseBean(map.get("code").toString(), map.get("message").toString());
    }

    @Desc("关锁")
    @ResponseBody
    @RequestMapping("closeLock")
    public ResponseBean closeLock(int lockId) throws Exception{
        //String response = HttpUtil.sendGet(relayUrl + "/userCloseLock?driveId="+lockId);
        System.out.println("sys-closeLock lockId：" + lockId);
        logger.info("logger.info---closeLock lockId：" + lockId);

        SysDeviceControl sysDeviceControl = relayService.getSysDeviceControl(lockId);
        String response = HttpUtil.sendGet(relayUrl + "/closeLock?did=" + sysDeviceControl.getDid() + "&lockId=" + sysDeviceControl.getLockId());
        Map map = JsonUtils.fromJson(response, Map.class);
        return new ResponseBean(map.get("code").toString(), map.get("message").toString());
    }

}
