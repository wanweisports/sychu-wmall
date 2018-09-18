package com.wardrobe.platform.service.impl;

import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.SysDeviceControl;
import com.wardrobe.common.po.SysDeviceInfo;
import com.wardrobe.platform.netty.client.ClientChannelUtil;
import com.wardrobe.platform.netty.client.NettyClient;
import com.wardrobe.platform.netty.client.bean.DeviceBean;
import com.wardrobe.platform.service.IRelayService;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cxs on 2018/9/10.
 */
@Service
public class RelayServiceImpl extends BaseService implements IRelayService {

    @Override
    public synchronized boolean connectServer(String ip, int port) throws Exception{
        Channel serverChannel = ClientChannelUtil.getServerChannel(ip, port);
        if(serverChannel == null) {
            NettyClient nettyClient = new NettyClient(ip, port);
            ChannelFuture future = nettyClient.clientServer();
            if(future != null && future.isSuccess()) {
                new Thread(){
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(999999999999999999L);
                                //future.channel().closeFuture().sync();
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                try{
                                    System.out.println(77777777777777777L);
                                    nettyClient.clientGroup.shutdownGracefully().sync();
                                    ClientChannelUtil.clearServerChannel(serverChannel);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();
                }
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
            DeviceBean deviceBean = ClientChannelUtil.readDriveStatus(serverChannel, lockId);
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
            DeviceBean deviceBean = ClientChannelUtil.readDriveStatus(serverChannel, lockId);
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
            serverChannel.writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.LOCK_OPEN + driveId, CharsetUtil.UTF_8));
        }else{
            throw new MessageException(ClientChannelUtil.getNowStatus(ip, port));
        }
    }

    @Override
    public synchronized void closeServerDrive(String ip, int port, int driveId){
        //判断是否连接中
        if(ClientChannelUtil.isOpen(ip, port)) {
            Channel serverChannel = ClientChannelUtil.getServerChannel(ip, port);
            serverChannel.writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.LOCK_CLOSE + driveId, CharsetUtil.UTF_8));
        }else{
            throw new MessageException(ClientChannelUtil.getNowStatus(ip, port));
        }
    }

    @Override
    public Map<String, Object> getRealyIndexsIn(){
        Map<String, Object> data = new HashMap<>();
        SysDeviceInfo sysDeviceInfo = baseDao.queryByHqlFirst("FROM SysDeviceInfo");
        if(sysDeviceInfo != null) {
            //获取8个柜子的状态
            String lockIp = sysDeviceInfo.getLockIp();
            Integer lockPort = sysDeviceInfo.getLockPort();
            data.put("deviceInfo", sysDeviceInfo);
            data.put("doorStatus", ClientChannelUtil.getNowStatus(sysDeviceInfo.getDoorIp(), sysDeviceInfo.getDoorPort()));
            data.put("lockStatus", ClientChannelUtil.getNowStatus(lockIp, lockPort));

            if(ClientChannelUtil.isOpen(lockIp, lockPort)) {
                List<SysDeviceControl> deviceControls = baseDao.queryByHql("FROM SysDeviceControl WHERE did = ?1", sysDeviceInfo.getDid());
                deviceControls.stream().forEach(deviceControl -> {
                    DeviceBean deviceBean = ClientChannelUtil.readDriveStatus(ClientChannelUtil.getServerChannel(lockIp, lockPort), deviceControl.getLockId());
                    deviceControl.setStatus(deviceBean.getStatus());
                });
            }
        }
        return data;
    }

}
