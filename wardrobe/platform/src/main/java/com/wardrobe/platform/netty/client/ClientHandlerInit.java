package com.wardrobe.platform.netty.client;

import com.wardrobe.platform.netty.reconnect.client.ConnectorIdleStateTrigger;
import com.wardrobe.platform.netty.reconnect.client.ConnectionWatchdog;
import com.wardrobe.platform.netty.reconnect.client.HeartBeatClientHandler;
import com.wardrobe.platform.service.ISysDeviceService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.HashedWheelTimer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

public class ClientHandlerInit extends ChannelInitializer<SocketChannel> {

    private String host;
    private int port;
    private Bootstrap boot;
    protected final HashedWheelTimer timer = new HashedWheelTimer();
    private final ConnectorIdleStateTrigger idleStateTrigger = new ConnectorIdleStateTrigger();
    private ISysDeviceService deviceService;

    public ClientHandlerInit(String host, int port, Bootstrap boot, ISysDeviceService deviceService) {
        this.host = host;
        this.port = port;
        this.boot = boot;
        this.deviceService = deviceService;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.println("客户端执行initChannel#############################" + boot);
        ChannelPipeline pipeline = ch.pipeline();
        // 以("\n")为结尾分割的 解码器
        //pipeline.addLast("framer", new DelimiterBasedFrameDecoder(115200, Delimiters.lineDelimiter()));
        // 字符串解码 和 编码
        /*pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringDecoder());*/
        ch.pipeline().addLast(new ConnectionWatchdog(boot, timer, port, host, true, deviceService) {

            public ChannelHandler[] handlers() {
                return new ChannelHandler[]{
                        this,
                        new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS),
                        idleStateTrigger,
                        new HeartBeatClientHandler()
                };
            }
        }.handlers());
        //自己的逻辑
        //pipeline.addLast(new ClientHandler()); 多余
    }

}
