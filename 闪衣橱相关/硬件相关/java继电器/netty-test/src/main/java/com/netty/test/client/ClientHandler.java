package com.netty.test.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 雷达 on 2018/7/12.
 */
@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    public static List<Channel> channels = new ArrayList<Channel>();

    /**
     * 向服务端发送数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("客户端与服务端通道-开启：" + channel.localAddress() + "channelActive");
        System.out.println("服务端IP:" + channel.remoteAddress());
        String sendInfo = "Hello 这里是客户端  你好啊！";
        System.out.println("客户端准备发送的数据包：" + sendInfo);
        channel.writeAndFlush(Unpooled.copiedBuffer(sendInfo, CharsetUtil.UTF_8)); // 必须有flush
        channels.add(channel);
    }

    /**
     * channel 通道 Inactive 不活跃的
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     */
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务端通道-关闭：" + ctx.channel().localAddress() + "channelInactive");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("读取客户端通道信息..");
        ByteBuf buf = msg.readBytes(msg.readableBytes());
        System.out.println("客户端接收到的服务端信息，数据包为:" + buf.toString(Charset.forName("utf-8")));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常退出:" + cause.getMessage());
        ctx.close();
    }

}
