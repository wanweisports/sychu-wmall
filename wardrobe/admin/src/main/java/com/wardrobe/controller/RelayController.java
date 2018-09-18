package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.util.StrUtil;
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

    @Desc("连接Tcp服务器")
    @ResponseBody
    @RequestMapping("connectServer")
    public ResponseBean connectServer(int type) throws Exception{ //1：锁  2：大门
        if(type == 1){
            boolean success = relayService.connectServer(relayIp, relayPort);
            return new ResponseBean(success);
        }else if(type == 2){
            boolean success = relayService.connectServer(gateIp, gatePort);
            return new ResponseBean(success);
        }
        return null;
    }

    @Desc("开启锁")
    @ResponseBody
    @RequestMapping("openLock")
    public ResponseBean openLock(int lockId) throws Exception{
        relayService.openServerLock(relayIp, relayPort, lockId);
        return new ResponseBean(true);
    }

    @Desc("关闭锁")
    @ResponseBody
    @RequestMapping("closeLock")
    public ResponseBean closeLock(int lockId){
        relayService.closeServerLock(relayIp, relayPort, lockId);
        return new ResponseBean(true);
    }

    @Desc("开启全部锁")
    @ResponseBody
    @RequestMapping("openAllLock")
    public ResponseBean openAllLock() throws Exception{
        relayService.openServerAllLock(relayIp, relayPort);
        return new ResponseBean(true);
    }

    @Desc("关闭全部锁")
    @ResponseBody
    @RequestMapping("closeAllLock")
    public ResponseBean closeAllLock(){
        relayService.closeServerAllLock(relayIp, relayPort);
        return new ResponseBean(true);
    }

    @Desc("开启门设备")
    @ResponseBody
    @RequestMapping("openDrive")
    public ResponseBean openDrive(int driveId) throws Exception{
        relayService.openServerDrive(gateIp, gatePort, driveId);
        return new ResponseBean(true);
    }

    @Desc("关闭门设备")
    @ResponseBody
    @RequestMapping("closeDrive")
    public ResponseBean closeDrive(int driveId) throws Exception{
        relayService.closeServerDrive(gateIp, gatePort, driveId);
        return new ResponseBean(true);
    }

    @Desc("获取锁连接状态")
    @ResponseBody
    @RequestMapping("getLockConnectStatus")
    public ResponseBean getLockConnectStatus(){
        return new ResponseBean(new HashMap(1,1){{put("statusName", ClientChannelUtil.getNowStatus(relayIp, relayPort));}});
    }

    @Desc("获取门连接状态")
    @ResponseBody
    @RequestMapping("getGateConnectStatus")
    public ResponseBean getGateConnectStatus(){
        return new ResponseBean(new HashMap(1,1){{put("statusName", ClientChannelUtil.getNowStatus(gateIp, gatePort));}});
    }

}
