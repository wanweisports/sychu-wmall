package com.wardrobe.platform.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;

/**
 * 执行顺序
 * 服务端执行initChannel#############################
 * 服务端handlerAdded
 * cs：1
 * /127.0.0.1:60269 active!
 * 服务端接收数据完毕..
 * /127.0.0.1:60269 inactive!
 * 服务端handlerRemoved
 */
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     * channel 通道 action 活跃的
     * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " active!");
        super.channelActive(ctx);
    }

    /**
     * channel 通道 Inactive 不活跃的
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " inactive!");
        channels.remove(ctx.channel());
        super.channelInactive(ctx);
    }

    /**
     * 功能：读取服务器发送过来的信息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf o) throws Exception {
        System.out.println(o);
        System.out.println(ctx.channel().remoteAddress() + "收到客户端消息：" + getMessage(o));

        //返回客户端消息
        ctx.writeAndFlush(sendMsg("服务端说：我收到你的消息了====="));
    }

    //待解决===channelActive====handlerAdded
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端handlerAdded");
        Channel incoming = ctx.channel();
        channels.add(incoming);
        System.out.println("cs：" + channels.size());
        for (Channel channel : channels){
            System.out.println("服务端handlerAdded：" + incoming.remoteAddress());
            channel.writeAndFlush(sendMsg("服务端说：欢迎[" + incoming.remoteAddress() + "]加入，当前总连接数：" + channels.size() + "\n"));
        }
        incoming.writeAndFlush(sendMsg("欢迎来到" + InetAddress.getLocalHost().getHostName() + " service!"));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端handlerRemoved");
        Channel leaving = ctx.channel();
        channels.remove(leaving);
        for (Channel channel : channels){
            System.out.println("handlerRemoved：" + leaving.remoteAddress());
            channel.writeAndFlush(sendMsg("服务端说：[" + leaving.remoteAddress() + "]离开，当前总连接数：" + channels.size()));
        }
    }

    /**
     * 功能：读取完毕客户端发送过来的数据之后的操作
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端接收数据完毕..");
        // 第一种方法：写一个空的buf，并刷新写出区域。完成后关闭sock channel连接。(主动关闭发消息过来的客户端)
        //ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        // ctx.flush();
        // ctx.flush(); //
        // 第二种方法：在client端关闭channel连接，这样的话，会触发两次channelReadComplete方法。
        // ctx.flush().close().sync(); // 第三种：改成这种写法也可以，但是这中写法，没有第一种方法的好。
    }

    /**
     * 功能：服务端发生异常的操作
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //ctx.close();
        channels.remove(ctx.channel());
        System.out.println(ctx.channel().remoteAddress() + "发生异常，退出，当前总连接数：" + channels.size());
        ctx.close();
    }

    /**
     * TODO  此处用来处理收到的数据中含有中文的时  出现乱码的问题
     */
    private String getMessage(ByteBuf buf) {
        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            return new String(con, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ByteBuf sendMsg(String sendInfo){
        return Unpooled.copiedBuffer(sendInfo, CharsetUtil.UTF_8);
    }

}
