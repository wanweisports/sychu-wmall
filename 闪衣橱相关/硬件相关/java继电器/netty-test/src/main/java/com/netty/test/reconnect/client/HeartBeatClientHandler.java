package com.netty.test.reconnect.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 心跳检测业务类
 */
public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("激活时间是：" + new Date());
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("停止时间是：" + new Date());
    }

    //当前面的通道channelRead后，后面的通道无法获得消息（不会调用后面通道channelRead接口）
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(1);
        //super.channelRead(ctx, msg); //接收消息后，其他Read方法则不能获取同一个消息[调用某个Read方法后，refCn=0]，否则报错
        //PooledUnsafeDirectByteBuf
        System.out.println("Heartbeat-client:" + msg);
        String message = getMessage((ByteBuf) msg);
        System.out.println("message-client:" + message);
        if(message.equals("Heartbeat")){
            ctx.write("has read message from server");
            ctx.flush();
        }else{

        }
        ReferenceCountUtil.release(msg);
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

}


























