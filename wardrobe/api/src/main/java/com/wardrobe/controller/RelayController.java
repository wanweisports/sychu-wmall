package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.annotation.Perfect;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.po.SysDeviceInfo;
import com.wardrobe.common.util.HttpUtil;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.platform.netty.client.ClientChannelUtil;
import com.wardrobe.platform.service.IRelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxs on 2018/9/10.
 */
@Controller
@RequestMapping("relay")
public class RelayController extends BaseController {

    @Autowired
    private IRelayService relayService;

    @Desc("开启门")
    @Perfect //必须完善资料
    @ResponseBody
    @RequestMapping("openDoor")
    public ResponseBean openDoor(int did) throws Exception{
        //String response = HttpUtil.sendGet(relayUrl + "/userOpenDrive?driveId="+did); //射频版
        String response = HttpUtil.sendGet(relayUrl + "/openDrive?driveId="+did);  //直接开门版
        Map map = JsonUtils.fromJson(response, Map.class);
        return new ResponseBean(map.get("code").toString(), map.get("message").toString());
    }

    @Desc("开启锁")
    @ResponseBody
    @RequestMapping("openLock")
    public ResponseBean openLock(int lockId) throws Exception{
        String response = HttpUtil.sendGet(relayUrl + "/userOpenLock?driveId="+lockId);
        Map map = JsonUtils.fromJson(response, Map.class);
        return new ResponseBean(map.get("code").toString(), map.get("message").toString());
    }

    @Desc("关锁")
    @ResponseBody
    @RequestMapping("closeLock")
    public ResponseBean closeLock(int lockId) throws Exception{
        String response = HttpUtil.sendGet(relayUrl + "/userCloseLock?driveId="+lockId);
        Map map = JsonUtils.fromJson(response, Map.class);
        return new ResponseBean(map.get("code").toString(), map.get("message").toString());
    }

    @Desc("关门")
    @ResponseBody
    @RequestMapping("closeDoor")
    public ResponseBean closeDoor(int did) throws Exception{
        //String response = HttpUtil.sendGet(relayUrl + "/userCloseDrive?driveId="+did); //射频版
        String response = HttpUtil.sendGet(relayUrl + "/closeDrive?driveId="+did); //直接关门版
        Map map = JsonUtils.fromJson(response, Map.class);
        return new ResponseBean(map.get("code").toString(), map.get("message").toString());
    }

}
