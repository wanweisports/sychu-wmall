package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.bean.UserDriveBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.SysDeviceInfo;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.netty.client.ClientChannelUtil;
import com.wardrobe.platform.service.IRelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    int did = 1;

    @Desc("连接Tcp服务器")
    @ResponseBody
    @RequestMapping("connectServer")
    public ResponseBean connectServer(int type) throws Exception{ //1：大门  2：锁
        SysDeviceInfo sysDeviceInfo = relayService.getSysDeviceInfo(did);
        if(type == 1){
            boolean success = relayService.connectServer(sysDeviceInfo.getDoorIp(), sysDeviceInfo.getDoorPort());
            return new ResponseBean(success);
        }else if(type == 2){
            boolean success = relayService.connectServer(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort());
            return new ResponseBean(success);
        }
        return null;
    }

    @Desc("开启锁")
    @ResponseBody
    @RequestMapping("openLock")
    public ResponseBean openLock(int lockId) throws Exception{
        SysDeviceInfo sysDeviceInfo = relayService.getSysDeviceInfo(did);
        relayService.openServerLock(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort(), lockId);
        return new ResponseBean(true);
    }

    @Desc("关闭锁")
    @ResponseBody
    @RequestMapping("closeLock")
    public ResponseBean closeLock(int lockId){
        SysDeviceInfo sysDeviceInfo = relayService.getSysDeviceInfo(did);
        relayService.closeServerLock(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort(), lockId);
        return new ResponseBean(true);
    }

    @Desc("开启全部锁")
    @ResponseBody
    @RequestMapping("openAllLock")
    public ResponseBean openAllLock() throws Exception{
        SysDeviceInfo sysDeviceInfo = relayService.getSysDeviceInfo(did);
        relayService.openServerAllLock(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort());
        return new ResponseBean(true);
    }

    @Desc("关闭全部锁")
    @ResponseBody
    @RequestMapping("closeAllLock")
    public ResponseBean closeAllLock(){
        SysDeviceInfo sysDeviceInfo = relayService.getSysDeviceInfo(did);
        relayService.closeServerAllLock(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort());
        return new ResponseBean(true);
    }

    @Desc("开启门设备")
    @ResponseBody
    @RequestMapping("openDrive")
    public ResponseBean openDrive(int driveId) throws Exception{
        SysDeviceInfo sysDeviceInfo = relayService.getSysDeviceInfo(did);
        relayService.openServerDrive(sysDeviceInfo.getDoorIp(), sysDeviceInfo.getDoorPort(), driveId);
        return new ResponseBean(true);
    }

    @Desc("关闭门设备")
    @ResponseBody
    @RequestMapping("closeDrive")
    public ResponseBean closeDrive(int driveId) throws Exception{
        SysDeviceInfo sysDeviceInfo = relayService.getSysDeviceInfo(did);
        relayService.closeServerDrive(sysDeviceInfo.getDoorIp(), sysDeviceInfo.getDoorPort(), driveId);
        return new ResponseBean(true);
    }

    @Desc("断开大门")
    @ResponseBody
    @RequestMapping("downlineDoor")
    public ResponseBean downlineDoor(){
        SysDeviceInfo sysDeviceInfo = relayService.getSysDeviceInfo(did);
        relayService.downlineRelay(sysDeviceInfo.getDoorIp(), sysDeviceInfo.getDoorPort());
        return new ResponseBean(true);
    }

    @Desc("断开柜子")
    @ResponseBody
    @RequestMapping("downlineLock")
    public ResponseBean downlineLock(){
        SysDeviceInfo sysDeviceInfo = relayService.getSysDeviceInfo(did);
        relayService.downlineRelay(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort());
        return new ResponseBean(true);
    }

    @Desc("获取门连接状态")
    @ResponseBody
    @RequestMapping("getGateConnectStatus")
    public ResponseBean getGateConnectStatus(){
        SysDeviceInfo sysDeviceInfo = relayService.getSysDeviceInfo(did);
        return new ResponseBean(new HashMap(1,1){{put("statusName", ClientChannelUtil.getNowStatus(sysDeviceInfo.getDoorIp(), sysDeviceInfo.getDoorPort()));}});
    }

    @Desc("获取锁连接状态")
    @ResponseBody
    @RequestMapping("getLockConnectStatus")
    public ResponseBean getLockConnectStatus(){
        SysDeviceInfo sysDeviceInfo = relayService.getSysDeviceInfo(did);
        return new ResponseBean(new HashMap(1,1){{put("statusName", ClientChannelUtil.getNowStatus(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort()));}});
    }

    //##################################################################################################################

    @Desc("用户开启门设备")
    @ResponseBody
    @RequestMapping("userOpenDrive")
    public ResponseBean userOpenDrive(UserDriveBean userDriveBean) throws Exception{
        try{
            userDriveBean.setSysDeviceInfo(relayService.getSysDeviceInfo(did));
            relayService.saveUserOpenServerDrive(userDriveBean);
            return new ResponseBean(true);
        }catch (MessageException e){
            return new ResponseBean(e.getMessage());
        }
    }

    @Desc("开启锁")
    @ResponseBody
    @RequestMapping("userOpenLock")
    public ResponseBean userOpenLock(UserDriveBean userDriveBean) throws Exception{
        try{
            userDriveBean.setSysDeviceInfo(relayService.getSysDeviceInfo(did));
            relayService.saveUserOpenServerLock(userDriveBean);
            return new ResponseBean(true);
        }catch (MessageException e){
            return new ResponseBean(e.getMessage());
        }
    }

    @Desc("关闭锁")
    @ResponseBody
    @RequestMapping("userCloseLock")
    public ResponseBean userCloseLock(UserDriveBean userDriveBean) throws Exception{
        try{
            userDriveBean.setSysDeviceInfo(relayService.getSysDeviceInfo(did));
            relayService.saveUserCloseServerLock(userDriveBean);
            return new ResponseBean(true);
        }catch (MessageException e){
            return new ResponseBean(e.getMessage());
        }
    }

    @Desc("用户关闭门设备")
    @ResponseBody
    @RequestMapping("userCloseDrive")
    public ResponseBean userCloseDrive(UserDriveBean userDriveBean) throws Exception{
        try{
            userDriveBean.setSysDeviceInfo(relayService.getSysDeviceInfo(did));
            relayService.saveUserCloseServerDrive(userDriveBean);
            return new ResponseBean(true);
        }catch (MessageException e){
            return new ResponseBean(e.getMessage());
        }
    }

}
