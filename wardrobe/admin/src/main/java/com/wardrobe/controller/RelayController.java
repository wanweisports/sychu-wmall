package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.annotation.NotProtected;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.bean.UserDriveBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.SysRfidInfo;
import com.wardrobe.platform.rfid.bean.RfidBean;
import com.wardrobe.platform.service.IRelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cxs on 2018/9/10.
 *  开大门：大门：https://mystore.yifoutech.com/relay/openDrive?driveId=1
    关大门：大门：https://mystore.yifoutech.com/relay/closeDrive?driveId=1
 */
@Controller
@RequestMapping("relay")
public class RelayController extends BaseController {

    @Autowired
    private IRelayService relayService;

    @Desc("连接Tcp服务器")
    @ResponseBody
    @RequestMapping("connectServer")
    public ResponseBean connectServer(int type) throws Exception{ //1：大门  2：锁
        /*SysDeviceInfo sysDeviceInfo = relayService.getSysDeviceInfo(did);
        if(type == 1){
            boolean success = relayService.connectServer(sysDeviceInfo.getDoorIp(), sysDeviceInfo.getDoorPort());
            return new ResponseBean(success);
        }else if(type == 2){
            boolean success = relayService.connectServer(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort());
            return new ResponseBean(success);
        }*/
        return null;
    }

    @Desc("开启门设备")
    @ResponseBody
    @NotProtected
    @RequestMapping("openDrive")
    public ResponseBean openDrive(int did) throws Exception{
        try {
            return new ResponseBean(relayService.openDoor(did));
        }catch (MessageException e){ //由于是api请求过来的接口，admin处理未按ajax处理错误，这里用try一下
            e.printStackTrace();
            return new ResponseBean(e.getMessage());
        }
    }

    @Desc("关闭门设备")
    @ResponseBody
    @NotProtected
    @RequestMapping("closeDrive")
    public ResponseBean closeDrive(int did) throws Exception{
        try {
            return new ResponseBean(relayService.closeDoor(did));
        }catch (MessageException e){ //由于是api请求过来的接口，admin处理未按ajax处理错误，这里用try一下
            e.printStackTrace();
            return new ResponseBean(e.getMessage());
        }
    }

    @Desc("开启锁")
    @ResponseBody
    @NotProtected
    @RequestMapping("openLock")
    public ResponseBean openLock(int did, int lockId) throws Exception{
        try {
            return new ResponseBean(relayService.openLock(did, lockId));
        }catch (MessageException e){ //由于是api请求过来的接口，admin处理未按ajax处理错误，这里用try一下
            e.printStackTrace();
            return new ResponseBean(e.getMessage());
        }
    }

    @Desc("关闭锁")
    @ResponseBody
    @NotProtected
    @RequestMapping("closeLock")
    public ResponseBean closeLock(int did, int lockId){
        try {
            return new ResponseBean(relayService.closeLock(did, lockId));
        }catch (MessageException e){ //由于是api请求过来的接口，admin处理未按ajax处理错误，这里用try一下
            e.printStackTrace();
            return new ResponseBean(e.getMessage());
        }
    }

    @Desc("测试读取射频电子标签")
    @ResponseBody
    @RequestMapping(value = "/readEpcLabelCK")
    public ResponseBean readEpcLabelCK(int did){
        return new ResponseBean(relayService.rfidRead(did));
    }

    @Desc("读取商场射频电子标签")
    @ResponseBody
    @NotProtected
    @RequestMapping(value = "/readEpcLabelSC")
    public ResponseBean readEpcLabelSC(int did){
        try {
            return new ResponseBean(relayService.readEpcLabelApi(did));
        }catch (MessageException e){ //由于是api请求过来的接口，admin处理未按ajax处理错误，这里用try一下
            e.printStackTrace();
            return new ResponseBean(e.getMessage());
        }
    }

    //##################################################################################################################

    @Desc("用户开启门设备")
    @ResponseBody
    @NotProtected
    @RequestMapping("userOpenDrive")
    public ResponseBean userOpenDrive(UserDriveBean userDriveBean) throws Exception{
        try{
            relayService.apiOpenDoor(userDriveBean);
            return new ResponseBean(true);
        }catch (MessageException e){ //由于是api请求过来的接口，admin处理未按ajax处理错误，这里用try一下
            return new ResponseBean(e.getMessage());
        }
    }

    @Desc("用户关闭门设备")
    @ResponseBody
    @NotProtected
    @RequestMapping("userCloseDrive")
    public ResponseBean userCloseDrive(UserDriveBean userDriveBean) throws Exception{
        try{
            relayService.apiCloseDoor(userDriveBean);
            return new ResponseBean(true);
        }catch (MessageException e){ //由于是api请求过来的接口，admin处理未按ajax处理错误，这里用try一下
            return new ResponseBean(e.getMessage());
        }
    }

    @Desc("用户开启锁")
    @ResponseBody
    @NotProtected
    @RequestMapping("userOpenLock")
    public ResponseBean userOpenLock(UserDriveBean userDriveBean) throws Exception{
        try{
            userDriveBean.setSysDeviceInfo(relayService.getSysDeviceInfo(userDriveBean.getDid()));
            relayService.userOpenLock(userDriveBean);
            return new ResponseBean(true);
        }catch (MessageException e){
            return new ResponseBean(e.getMessage());
        }
    }

    @Desc("用户关闭锁")
    @ResponseBody
    @NotProtected
    @RequestMapping("userCloseLock")
    public ResponseBean userCloseLock(UserDriveBean userDriveBean) throws Exception{
        try{
            userDriveBean.setSysDeviceInfo(relayService.getSysDeviceInfo(userDriveBean.getDid()));
            relayService.saveUserCloseServerLock(userDriveBean);
            return new ResponseBean(true);
        }catch (MessageException e){
            return new ResponseBean(e.getMessage());
        }
    }

}
