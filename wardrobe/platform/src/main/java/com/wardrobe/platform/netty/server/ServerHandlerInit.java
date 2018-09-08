package com.wardrobe.platform.netty.server;

import com.wardrobe.platform.netty.reconnect.server.HeartBeatServerHandler;
import com.wardrobe.platform.netty.reconnect.server.AcceptorIdleStateTrigger;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by 雷达 on 2018/7/12.
 */
public class ServerHandlerInit extends ChannelInitializer<SocketChannel> {

    private final AcceptorIdleStateTrigger idleStateTrigger = new AcceptorIdleStateTrigger();

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.println("服务端执行initChannel#############################");
        ChannelPipeline pipeline = ch.pipeline();
        // 以("\n")为结尾分割的 解码器
        //pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        // 字符串解码 和 编码
        /*pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringDecoder());*/
        pipeline.addLast(new IdleStateHandler(8, 0, 0, TimeUnit.SECONDS));
        pipeline.addLast(idleStateTrigger);
        pipeline.addLast(new HeartBeatServerHandler());
        //自己的逻辑
        pipeline.addLast(new ServerHandler());
    }

}


















