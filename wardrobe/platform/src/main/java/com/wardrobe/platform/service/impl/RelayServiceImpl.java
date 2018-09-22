package com.wardrobe.platform.service.impl;

import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.ReserveOrderInfo;
import com.wardrobe.common.po.SysDeviceControl;
import com.wardrobe.common.po.SysDeviceInfo;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public synchronized boolean connectServer(String ip, int port) throws Exception{
        Channel serverChannel = ClientChannelUtil.getServerChannel(ip, port);
        if(serverChannel == null) {
            NettyClient nettyClient = new NettyClient(ip, port);
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
                serverChannel.writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.LOCK_OPEN + lockId, CharsetUtil.UTF_8));
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
                serverChannel.writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.LOCK_OPEN + driveId, CharsetUtil.UTF_8));
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
                SysDeviceControl doorDeviceBean = ClientChannelUtil.readDriveStatus(ClientChannelUtil.getServerChannel(lockIp, lockPort), 1);
                data.put("doorDeviceBean", doorDeviceBean);
            }

            //获取锁的状态
            if(ClientChannelUtil.isOpen(lockIp, lockPort)) {
                List<SysDeviceControl> deviceControls = baseDao.queryByHql("FROM SysDeviceControl WHERE did = ?1", sysDeviceInfo.getDid());
                deviceControls.stream().forEach(deviceControl -> {
                    SysDeviceControl deviceBean = ClientChannelUtil.readDriveStatus(ClientChannelUtil.getServerChannel(lockIp, lockPort), deviceControl.getLockId());
                    deviceControl.setStatus(deviceBean.getStatus());
                });
                data.put("deviceControls", deviceControls);
            }
        }
        return data;
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

}
