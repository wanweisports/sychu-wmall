package com.wardrobe.platform.service.impl;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.UserDriveBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.ReserveOrderInfo;
import com.wardrobe.common.po.SysDeviceControl;
import com.wardrobe.common.po.SysDeviceInfo;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.platform.netty.client.ClientChannelUtil;
import com.wardrobe.platform.netty.client.NettyClient;
import com.wardrobe.platform.netty.client.bean.DeviceBean;
import com.wardrobe.platform.service.IOrderService;
import com.wardrobe.platform.service.IRelayService;
import com.wardrobe.platform.service.ISysDeviceService;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by cxs on 2018/9/10.
 */
@Service
public class RelayServiceImpl extends BaseService implements IRelayService {

    @Autowired
    private ISysDeviceService deviceService;

    @Autowired
    private IOrderService orderService;

    @Override
    public SysDeviceInfo getSysDeviceInfo(int did){
        return deviceService.getSysDeviceInfo(did);
    }

    @Override
    public synchronized boolean connectServer(String ip, int port) throws Exception{
        Channel serverChannel = ClientChannelUtil.getServerChannel(ip, port);
        if(serverChannel == null) {
            NettyClient nettyClient = new NettyClient(ip, port, deviceService);
            ChannelFuture future = nettyClient.clientServer();
            return future != null && future.isSuccess();
        }else{
            throw new MessageException(ClientChannelUtil.getNowStatus(ip, port));
        }
    }

    @Override
    public synchronized void openServerLock(String ip, int port, int lockId) throws Exception{
        //判断是否连接中
        if(ClientChannelUtil.isOpen(ip, port)) {
            Channel serverChannel = ClientChannelUtil.getServerChannel(ip, port);
            //判断是否为关闭状态才能打开
            SysDeviceControl deviceBean = ClientChannelUtil.readDriveStatus(serverChannel, lockId);
            if(ClientChannelUtil.READ_CLOSE.equals(deviceBean.getStatus())) {
                serverChannel.writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.PULSE_OPEN + lockId, CharsetUtil.UTF_8));
            }
        }else{
            throw new MessageException(ClientChannelUtil.getNowStatus(ip, port));
        }
    }

    @Override
    public synchronized void closeServerLock(String ip, int port, int lockId){
        //判断是否连接中
        if(ClientChannelUtil.isOpen(ip, port)) {
            Channel serverChannel = ClientChannelUtil.getServerChannel(ip, port);
            //判断是否为打开状态才能关闭
            SysDeviceControl deviceBean = ClientChannelUtil.readDriveStatus(serverChannel, lockId);
            if(ClientChannelUtil.READ_OPEN.equals(deviceBean.getStatus())) {
                serverChannel.writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.LOCK_CLOSE + lockId, CharsetUtil.UTF_8));
            }
        }else{
            throw new MessageException(ClientChannelUtil.getNowStatus(ip, port));
        }
    }

    @Override
    public synchronized void openServerAllLock(String ip, int port){
        //判断是否连接中
        if(ClientChannelUtil.isOpen(ip, port)) {
            Channel serverChannel = ClientChannelUtil.getServerChannel(ip, port);
            serverChannel.writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.LOCK_OPEN_ALL, CharsetUtil.UTF_8));
        }else{
            throw new MessageException(ClientChannelUtil.getNowStatus(ip, port));
        }
    }

    @Override
    public synchronized void closeServerAllLock(String ip, int port){
        //判断是否连接中
        if(ClientChannelUtil.isOpen(ip, port)) {
            Channel serverChannel = ClientChannelUtil.getServerChannel(ip, port);
            serverChannel.writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.LOCK_CLOSE_ALL, CharsetUtil.UTF_8));
        }else{
            throw new MessageException(ClientChannelUtil.getNowStatus(ip, port));
        }
    }

    @Override
    public synchronized void openServerDrive(String ip, int port, int driveId){
        //判断是否连接中
        if(ClientChannelUtil.isOpen(ip, port)) {
            Channel serverChannel = ClientChannelUtil.getServerChannel(ip, port);
            //判断是否为关闭状态才能打开
            SysDeviceControl deviceBean = ClientChannelUtil.readDriveStatus(serverChannel, driveId);
            if(ClientChannelUtil.READ_CLOSE.equals(deviceBean.getStatus())) {
                if(driveId == 6){
                    serverChannel.writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.PULSE_OPEN + driveId, CharsetUtil.UTF_8)); //大门电脉冲
                }else {
                    serverChannel.writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.LOCK_OPEN + driveId, CharsetUtil.UTF_8));  //开大门依然不变
                }
            }
        }else{
            throw new MessageException(ClientChannelUtil.getNowStatus(ip, port));
        }
    }

    @Override
    public synchronized void closeServerDrive(String ip, int port, int driveId){
        //判断是否连接中
        if(ClientChannelUtil.isOpen(ip, port)) {
            Channel serverChannel = ClientChannelUtil.getServerChannel(ip, port);
            //判断是否为打开状态才能关闭
            SysDeviceControl deviceBean = ClientChannelUtil.readDriveStatus(serverChannel, driveId);
            if(ClientChannelUtil.READ_OPEN.equals(deviceBean.getStatus())) {
                serverChannel.writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.LOCK_CLOSE + driveId, CharsetUtil.UTF_8));
            }
        }else{
            throw new MessageException(ClientChannelUtil.getNowStatus(ip, port));
        }
    }

    @Override
    public Map<String, Object> getRealyIndexsIn(){
        Map<String, Object> data = new HashMap<>(5, 1);
        SysDeviceInfo sysDeviceInfo = baseDao.queryByHqlFirst("FROM SysDeviceInfo");
        if(sysDeviceInfo != null) {
            //获取8个柜子的状态
            String doorIp = sysDeviceInfo.getDoorIp();
            Integer doorPort = sysDeviceInfo.getDoorPort();
            String lockIp = sysDeviceInfo.getLockIp();
            Integer lockPort = sysDeviceInfo.getLockPort();
            data.put("deviceInfo", sysDeviceInfo);
            data.put("doorStatus", ClientChannelUtil.getNowStatus(doorIp, doorPort)); //门连接状态
            data.put("lockStatus", ClientChannelUtil.getNowStatus(lockIp, lockPort)); //锁连接状态

            //获取门的状态
            if(ClientChannelUtil.isOpen(doorIp, doorPort)) {
                List<SysDeviceControl> deviceDoorControls = baseDao.queryByHql("FROM SysDeviceControl WHERE did = ?1 AND type = ?2", sysDeviceInfo.getDid(), IDBConstant.LOGIC_STATUS_YES);
                deviceDoorControls.stream().forEach(deviceControl -> {
                    SysDeviceControl deviceBean = ClientChannelUtil.readDriveStatus(ClientChannelUtil.getServerChannel(doorIp, doorPort), deviceControl.getLockId());
                    deviceControl.setStatus(deviceBean.getStatus());
                });
                data.put("deviceDoorControls", deviceDoorControls);
            }

            //获取锁的状态
            data.put("deviceControls", getAllDeviceControls(lockIp, lockPort, sysDeviceInfo.getDid()));
        }
        return data;
    }

    private List<SysDeviceControl> getAllDeviceControls(String lockIp, int lockPort, int did){
        if(ClientChannelUtil.isOpen(lockIp, lockPort)) {
            List<SysDeviceControl> deviceControls = baseDao.queryByHql("FROM SysDeviceControl WHERE did = ?1 AND type = ?2", did, IDBConstant.LOGIC_STATUS_NO);
            deviceControls.stream().forEach(deviceControl -> {
                SysDeviceControl deviceBean = ClientChannelUtil.readDriveStatus(ClientChannelUtil.getServerChannel(lockIp, lockPort), deviceControl.getLockId());
                deviceControl.setStatus(deviceBean.getStatus());
            });
            return deviceControls;
        }
        return null;
    }

    @Override
    public void openDoor(int did, int uid){
        SysDeviceInfo sysDeviceInfo = deviceService.getSysDeviceInfo(did);
        ReserveOrderInfo reserveOrderInfo = orderService.getLastReserveOrderInfo(uid);
        //1.判断射频（除了已支付的衣服标签，是否都检查到了，再开门）
        if(1==1){}
        //2.判断自己的柜子已经关闭
        //  A.查询当前预约的订单
        SysDeviceControl deviceBean = ClientChannelUtil.readDriveStatus(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort(), reserveOrderInfo.getDcid());
        if(!ClientChannelUtil.READ_CLOSE.equals(deviceBean.getStatus())) throw new MessageException("出门前请您关闭柜子");
        if(1==1) {
            try {
                openServerDrive(sysDeviceInfo.getDoorIp(), sysDeviceInfo.getDoorPort(), 1);
            } catch (MessageException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void closeDoor(int did, int uid){
        SysDeviceInfo sysDeviceInfo = deviceService.getSysDeviceInfo(did);
        try {
            closeServerDrive(sysDeviceInfo.getDoorIp(), sysDeviceInfo.getDoorPort(), 1);
        } catch (MessageException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openLock(int dcid) throws Exception{
        SysDeviceControl sysDeviceControl = deviceService.getSysDeviceControl(dcid);
        SysDeviceInfo sysDeviceInfo = deviceService.getSysDeviceInfo(sysDeviceControl.getDid());
        try {
            openServerLock(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort(), dcid);
        } catch (MessageException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeLock(int dcid) throws Exception{
        SysDeviceControl sysDeviceControl = deviceService.getSysDeviceControl(dcid);
        SysDeviceInfo sysDeviceInfo = deviceService.getSysDeviceInfo(sysDeviceControl.getDid());
        try {
            closeServerLock(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort(), dcid);
        } catch (MessageException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downlineRelay(String ip, int port){
        Channel serverChannel = ClientChannelUtil.getServerChannel(ip, port);
        if(serverChannel != null){
            serverChannel.eventLoop().parent().shutdownGracefully();
        }
    }

    @Desc("扫码开门==进门")
    @Override
    public synchronized void saveUserOpenServerDrive(UserDriveBean userDriveBean) throws Exception{
        SysDeviceInfo sysDeviceInfo = userDriveBean.getSysDeviceInfo();
        //2. 试衣间可用？
        if(!IDBConstant.LOGIC_STATUS_YES.equals(sysDeviceInfo.getStatus())){ //N: 提示 试衣间当前不可用，请稍后再试
            Date datePrev15 = DateUtil.addHHMMTime(new Date(), Calendar.MINUTE, -16);
            Timestamp openLockTime = sysDeviceInfo.getOpenLockTime();
            if(openLockTime != null && openLockTime.after(datePrev15)) { //开门十五分钟内是否扫码开柜门？N：试衣间状态更新为启用【以当前时间，15分钟以后未开柜子，则算没有人使用】
                throw new MessageException("试衣间当前不可用，请稍后再试！");
            }
        }
        //3. 服务端下发（进）大门开门指令
        try{
            openServerDrive(sysDeviceInfo.getDoorIp(), sysDeviceInfo.getDoorPort(), userDriveBean.getDriveId());
        }catch (MessageException e){
            e.printStackTrace();
        }
        //4. 调用RIDF记录当前库存状态

        //5.试衣间状态更新为占用
        Timestamp nowDate = DateUtil.getNowDate();
        sysDeviceInfo.setOpenTime(nowDate);
        sysDeviceInfo.setStatus(IDBConstant.LOGIC_STATUS_NO); //占用
        sysDeviceInfo.setOpenLockTime(nowDate);
        baseDao.save(sysDeviceInfo, sysDeviceInfo.getDid());


    }

    @Desc("扫码开柜门")
    @Override
    public synchronized void saveUserOpenServerLock(UserDriveBean userDriveBean) throws Exception {
        SysDeviceInfo sysDeviceInfo = userDriveBean.getSysDeviceInfo();
        //7.下发柜门开门指令
        try{
            openServerLock(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort(), userDriveBean.getDriveId());
        }catch (MessageException e){
            e.printStackTrace();
        }
        Timestamp nowDate = DateUtil.getNowDate();
        SysDeviceControl sysDeviceControl = deviceService.getSysDeviceControl(userDriveBean.getDriveId());
        sysDeviceControl.setOpenTime(nowDate);
        sysDeviceControl.setLock(IDBConstant.LOGIC_STATUS_YES); //开启
        baseDao.save(sysDeviceControl, sysDeviceControl.getDcid());

        sysDeviceInfo.setOpenLockTime(nowDate);
        baseDao.save(sysDeviceInfo, sysDeviceInfo.getDid());
        //8.用户试衣


    }

    @Desc("扫码关柜门")
    @Override
    public synchronized void saveUserCloseServerLock(UserDriveBean userDriveBean) throws Exception {
        SysDeviceInfo sysDeviceInfo = userDriveBean.getSysDeviceInfo();
        //7.下发柜门开门指令
        try{
            closeServerLock(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort(), userDriveBean.getDriveId());
        }catch (MessageException e){
            e.printStackTrace();
        }
        SysDeviceControl sysDeviceControl = deviceService.getSysDeviceControl(userDriveBean.getDriveId());
        sysDeviceControl.setCloseTime(DateUtil.getNowDate());
        sysDeviceControl.setLock(IDBConstant.LOGIC_STATUS_NO); //锁住
        baseDao.save(sysDeviceControl, sysDeviceControl.getDcid());
    }

    @Desc("扫码开门==出门")
    @Override
    public synchronized void saveUserCloseServerDrive(UserDriveBean userDriveBean) throws Exception{
        SysDeviceInfo sysDeviceInfo = userDriveBean.getSysDeviceInfo();

        //9.出门与支付
        //判断是否所有锁关闭状态才能打开（出）大门
        List<SysDeviceControl> allDeviceControls = getAllDeviceControls(sysDeviceInfo.getLockIp(), sysDeviceInfo.getLockPort(), sysDeviceInfo.getDid());
        StringBuilder msg = new StringBuilder();
        if(allDeviceControls != null){
            allDeviceControls.stream().forEach(deviceControl -> {
                if (ClientChannelUtil.READ_OPEN.equals(deviceControl.getStatus())) {
                    if(msg.length() > 0) msg.append("、");
                    msg.append(deviceControl.getName());
                }
            });
        }
        if(msg.length() > 0){ //检查柜门关闭状态已全部关闭？N： 提示 请关好柜门再出门
            //检测所有柜子是否已经关闭
            throw new MessageException("出门前请关好柜子：" + msg.toString());
        }

        //10.调用RIDF检查商品库存变化

        if(1==1){ //库存有变化？N：下发大门开门指令，提示感谢使用

        }else{
            //11.创建试衣结算订单【这里需要检测拿走了哪些衣服，必须和标签做对应】

        }

        //服务端下发（出）大门开门指令
        try{
            openServerDrive(sysDeviceInfo.getDoorIp(), sysDeviceInfo.getDoorPort(), userDriveBean.getDriveId());
        }catch (MessageException e){
            e.printStackTrace();
        }

        //试衣间状态更新为解除占用
        sysDeviceInfo.setCloseTime(DateUtil.getNowDate());
        sysDeviceInfo.setStatus(IDBConstant.LOGIC_STATUS_NO); //解除占用
        sysDeviceInfo.setOpenLockTime(null);
        baseDao.save(sysDeviceInfo, sysDeviceInfo.getDid());
    }

}
