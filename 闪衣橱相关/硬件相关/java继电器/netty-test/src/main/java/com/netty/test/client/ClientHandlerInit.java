package com.netty.test.client;

import com.netty.test.reconnect.client.ConnectionWatchdog;
import com.netty.test.reconnect.client.ConnectorIdleStateTrigger;
import com.netty.test.reconnect.client.HeartBeatClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.HashedWheelTimer;

import java.util.concurrent.TimeUnit;

/**
 * Created by 雷达 on 2018/7/12.
 */
public class ClientHandlerInit extends ChannelInitializer<SocketChannel> {

    private String host;
    private int port;
    private Bootstrap boot;
    protected final HashedWheelTimer timer = new HashedWheelTimer();
    private final ConnectorIdleStateTrigger idleStateTrigger = new ConnectorIdleStateTrigger();

    public ClientHandlerInit(String host, int port, Bootstrap boot) {
        this.host = host;
        this.port = port;
        this.boot = boot;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.println("客户端执行initChannel#############################" + boot);
        ChannelPipeline pipeline = ch.pipeline();
        /*// 以("\n")为结尾分割的 解码器
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        // 字符串解码 和 编码
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringDecoder());*/
        ch.pipeline().addLast(new ConnectionWatchdog(boot, timer, port, host, true) {

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
        pipeline.addLast(new ClientHandler());
    }

}
