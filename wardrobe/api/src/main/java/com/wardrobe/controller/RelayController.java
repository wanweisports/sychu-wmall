package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.platform.netty.client.ClientChannelUtil;
import com.wardrobe.platform.service.IRelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by cxs on 2018/9/10.
 */
@Controller("relay")
public class RelayController extends BaseController {

    @Autowired
    private IRelayService relayService;

    @Desc("连接Tcp服务器")
    @ResponseBody
    @RequestMapping("connectServer")
    public ResponseBean connectServer(int type) throws Exception{ //1：锁  2：大门
        if(type == 1){
            relayService.connectServer(relayIp, relayPort);
        }else if(type == 2){
            relayService.connectServer(gateIp, gatePort);
        }
        return new ResponseBean(true);
    }

    @Desc("开启锁")
    @ResponseBody
    @RequestMapping("openLock")
    public ResponseBean openLock(int lockId){
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
