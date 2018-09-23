package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
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

    private String relayUrl = "http://localhost:8082/relay";

    @Desc("开启锁")
    @ResponseBody
    @RequestMapping("openLock")
    public ResponseBean openLock(int lockId) throws Exception{
        String response = HttpUtil.sendGet(relayUrl + "/openLock?lockId="+lockId);
        return new ResponseBean(IDBConstant.LOGIC_STATUS_YES.equals(JsonUtils.fromJson(response, Map.class).get("code")));
    }

    @Desc("开启门")
    @ResponseBody
    @RequestMapping("openDoor")
    public ResponseBean openDoor() throws Exception{
        String response = HttpUtil.sendGet(relayUrl + "/relay/openDrive?driveId=1");
        System.out.println(response);
        return new ResponseBean(IDBConstant.LOGIC_STATUS_YES.equals(JsonUtils.fromJson(response, Map.class).get("code")));
    }

    @Desc("关锁")
    @ResponseBody
    @RequestMapping("closeLock")
    public ResponseBean closeLock(int lockId) throws Exception{
        String response = HttpUtil.sendGet(relayUrl + "/relay/closeLock?lockId="+lockId);
        return new ResponseBean(IDBConstant.LOGIC_STATUS_YES.equals(JsonUtils.fromJson(response, Map.class).get("code")));
    }

    @Desc("关门")
    @ResponseBody
    @RequestMapping("closeDoor")
    public ResponseBean closeDoor() throws Exception{
        String response = HttpUtil.sendGet(relayUrl + "/relay/closeDrive?driveId=1");
        return new ResponseBean(IDBConstant.LOGIC_STATUS_YES.equals(JsonUtils.fromJson(response, Map.class).get("code")));
    }

}
