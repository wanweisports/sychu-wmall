package com.wardrobe.platform.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

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
        System.out.println("channel===>" + channel);
        ClientChannelUtil.connectServerChannel(channel);
    }

    /**
     * channel 通道 Inactive 不活跃的
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     */
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务端通道-关闭：" + ctx.channel().localAddress() + "channelInactive");
        ClientChannelUtil.clearServerChannel(ctx.channel());
    }

    /*
    L1：
    读取客户端通道信息..
    客户端接收到的服务端信息，数据包为:
    Relayon 1
    D：1
    读取客户端通道信息..
    客户端接收到的服务端信息，数据包为:
    Relayoff 1
    ！！！重连后，服务端发送消息给客户端，此方法不会在Read消息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("读取客户端通道信息..");
        ByteBuf buf = msg.readBytes(msg.readableBytes());
        String msgStr = buf.toString(Charset.forName("utf-8"));
        System.out.println("客户端接收到的服务端信息，数据包为:" + msgStr);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常退出:" + cause.getMessage());
        ctx.close();
        Channel channel = ctx.channel();
        System.out.println("channel===>" + channel);
        ClientChannelUtil.clearServerChannel(channel);
    }

}
