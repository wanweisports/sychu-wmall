package com.wardrobe.platform.service.impl;

import com.wardrobe.common.exception.MessageException;
import com.wardrobe.platform.netty.client.ClientChannelUtil;
import com.wardrobe.platform.netty.client.NettyClient;
import com.wardrobe.platform.service.IRelayService;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Service;

/**
 * Created by cxs on 2018/9/10.
 */
@Service
public class RelayServiceImpl extends BaseService implements IRelayService {

    @Override
    public void connectServer(String ip, int port) throws Exception{
        Channel serverChannel = ClientChannelUtil.getServerChannel(ip, port);
        if(serverChannel == null) {
            NettyClient nettyClient = new NettyClient(ip, port);
            nettyClient.clientServer();
        }else{
            throw new MessageException(ClientChannelUtil.getNowStatus(ip, port));
        }
    }

    @Override
    public void openServerLock(String ip, int port, int lockId){
        //判断是否连接中
        if(ClientChannelUtil.isOpen(ip, port)) {
            //判断是否为关闭状态才能开
            if(1==1) {
                ClientChannelUtil.getServerChannel(ip, port).writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.LOCK_OPEN + lockId, CharsetUtil.UTF_8));
            }
        }
    }

    @Override
    public void closeServerLock(String ip, int port, int lockId){
        //判断是否连接中
        if(ClientChannelUtil.isOpen(ip, port)) {
            //判断是否为开启状态才能关

            ClientChannelUtil.getServerChannel(ip, port).writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.LOCK_CLOSE + lockId, CharsetUtil.UTF_8));
        }
    }


}
