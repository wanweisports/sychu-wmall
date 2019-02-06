package com.wardrobe.platform.netty.reconnect.client;

import com.wardrobe.platform.netty.client.ClientChannelUtil2;
import com.wardrobe.platform.netty.client.bean.ClientBean;
import com.wardrobe.platform.rfid.util.StringTool;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 心跳检测业务类
 */
public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = Logger.getLogger(HeartBeatClientHandler.class);

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
        try{
            /*logger.info("===channelRead===");
            logger.info("Heartbeat-client:" + msg);
            String message = getMessage((ByteBuf) msg);
            logger.info("message-client:" + message);
            if(message.equals("Heartbeat")){
                ctx.write("has read message from server");
                ctx.flush();
            }else{ //获取设备状态
                if(message != null && message.contains("Relay")){
                    //0：状态  1：设备号[1-8]
                    String[] statusName = message.split(" ");
                    *//*SysDeviceControl deviceBean = ClientChannelUtil.getDeviceBean(ctx.channel(), StrUtil.objToInt(statusName[1]));
                    if(deviceBean != null){
                        deviceBean.setStatus(statusName[0].replace("\r\n", StrUtil.EMPTY));
                    }*//*
                }
            }*/
            String message = getMessage((ByteBuf) msg);
            String[] strAryHex = message.split(" ");
            ClientBean clientBean = ClientChannelUtil2.getClientBeanByMsg(message);
            String comm = strAryHex[6];
            //模拟数据
            if("DA".equals(comm)){
                String[] comms = "A5 5A 1F 00 00 01 DA 01 FA FF".split(" ");
                ctx.channel().writeAndFlush(Unpooled.copiedBuffer(StringTool.stringArrayToByteArray(comms, comms.length)));
            }
            if("DB".equals(comm)){
                String[] comms = "A5 5A 1F 00 00 01 DB 01 01 FD FF".split(" ");
                ctx.channel().writeAndFlush(Unpooled.copiedBuffer(StringTool.stringArrayToByteArray(comms, comms.length)));
            }
            if("DC".equals(comm)){
                String[] comms = "A5 5A 1F 00 00 01 DC DA 00 DB 01 01 DB 02 01 DB 03 01 DB 04 01 DB 05 01 DB 06 01 DB 07 00 DB 08 01 FA FF".split(" ");
                ctx.channel().writeAndFlush(Unpooled.copiedBuffer(StringTool.stringArrayToByteArray(comms, comms.length)));
            }
            if("DD".equals(comm)){
                String[] comms = "A5 5A 1F 00 00 01 DD 05 01 02 03 04 05 FA FF".split(" ");
                ctx.channel().writeAndFlush(Unpooled.copiedBuffer(StringTool.stringArrayToByteArray(comms, comms.length)));
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("channelRead===>" + e.getMessage());
        }finally {
            try{
                ReferenceCountUtil.release(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("异常退出123:" + cause.getMessage());
        Channel channel = ctx.channel();
        if(channel.isActive()) ctx.close();
        logger.error("channel===>" + channel);
        //ClientChannelUtil.clearServerChannel(channel);
    }

    /**
     * TODO  此处用来处理收到的数据中含有中文的时  出现乱码的问题
     */
    private String getMessage(ByteBuf buf) {
        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            //return new String(con, "UTF-8");
            return StringTool.byteArrayToString(con, 0, con.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}


























