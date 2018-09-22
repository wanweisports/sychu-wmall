package com.wardrobe.platform.netty.reconnect.client;

import com.wardrobe.common.po.SysDeviceControl;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.netty.client.ClientChannelUtil;
import com.wardrobe.platform.netty.client.bean.ClientBean;
import com.wardrobe.platform.netty.client.bean.DeviceBean;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
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
        System.out.println("停止时间是123：" + new Date());
    }

    //当前面的通道channelRead后，后面的通道无法获得消息（不会调用后面通道channelRead接口）
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //super.channelRead(ctx, msg); //不能打开，否则ref=0（被此方法读取过），则报错
        System.out.println("===channelRead===");

        System.out.println("Heartbeat-client:" + msg);
        String message = getMessage((ByteBuf) msg);
        System.out.println("message-client:" + message);
        if(message.equals("Heartbeat")){
            ctx.write("has read message from server");
            ctx.flush();
        }else{ //获取设备状态
            if(message != null && message.contains("Relay")){
                //0：状态  1：设备号[1-8]
                String[] statusName = message.split(" ");
                SysDeviceControl deviceBean = ClientChannelUtil.getDeviceBean(ctx.channel(), StrUtil.objToInt(statusName[1]));
                if(deviceBean != null){
                    deviceBean.setStatus(statusName[0].replace("\r\n", StrUtil.EMPTY));
                }
            }
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


























