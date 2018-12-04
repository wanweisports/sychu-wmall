package com.wardrobe.platform.netty.reconnect.client;

import com.wardrobe.platform.netty.client.ClientChannelUtil;
import com.wardrobe.platform.netty.client.bean.ClientBean;
import com.wardrobe.platform.service.ISysDeviceService;
import com.wardrobe.platform.service.impl.SysDeviceServiceImpl;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * 自动重连处理类
 */
@ChannelHandler.Sharable
public abstract class ConnectionWatchdog extends ChannelInboundHandlerAdapter implements TimerTask, ChannelHandlerHolder {

    private final Bootstrap bootstrap;
    private final Timer timer;
    private final int port;

    private final String host;

    private volatile boolean reconnect = true;
    private int attempts; //重连次数
    private ISysDeviceService deviceService;

    public ConnectionWatchdog(Bootstrap bootstrap, Timer timer, int port, String host, boolean reconnect, ISysDeviceService deviceService) {
        this.bootstrap = bootstrap;
        this.timer = timer;
        this.port = port;
        this.host = host;
        this.reconnect = reconnect;
        this.deviceService = deviceService;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("当前链路已经激活了，重连尝试次数重新置为0");

        attempts = 0;
        ctx.fireChannelActive();
        try{
            Channel channel = ctx.channel();
            InetSocketAddress socketAddress = (InetSocketAddress)channel.remoteAddress();
            ClientChannelUtil.connectServerChannel(channel, deviceService.getDeviceControl(socketAddress.getHostString(), socketAddress.getPort()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("链路关闭");
        ClientChannelUtil.clearServerChannel(ctx.channel());
        if(reconnect){
            System.out.println("链路关闭，将进行重连" + attempts);
            //if(attempts < 12){
            attempts++;
            //重连的间隔时间会越来越长
            int timeout = 3;
            timer.newTimeout(this, timeout, TimeUnit.SECONDS);
            //}
        }
        ctx.fireChannelInactive();
    }

    public void run(Timeout timeout) throws Exception {
        System.out.println("bootstrap:" + bootstrap);
        ChannelFuture future;
        //bootstrap已经初始化好了，只需要将handler填入就可以了
        synchronized (bootstrap){
            bootstrap.handler(new ChannelInitializer<Channel>() {

                @Override
                protected void initChannel(Channel ch) throws Exception {

                    ch.pipeline().addLast(handlers());
                }
            });
            future = bootstrap.connect(host, port);
        }
        //future对象
        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture f) throws Exception {
                boolean succed = f.isSuccess();

                //如果重连失败，则调用ChannelInactive方法，再次出发重连事件，一直尝试12次，如果失败则不再重连
                if(!succed){
                    System.out.println("重连失败");
                    f.channel().pipeline().fireChannelInactive();
                    ClientChannelUtil.clearServerChannel(f.channel());
                }else{
                    System.out.println("重连成功");
                }
            }
        });
    }
}











































































