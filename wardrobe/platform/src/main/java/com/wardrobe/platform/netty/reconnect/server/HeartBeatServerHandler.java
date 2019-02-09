package com.wardrobe.platform.netty.reconnect.server;

import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.netty.client.ClientChannelUtil2;
import com.wardrobe.platform.netty.client.bean.ClientBean;
import com.wardrobe.platform.rfid.util.StringTool;
import com.wardrobe.platform.service.ISysDeviceService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 心跳检测业务处理类
 */
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private ISysDeviceService sysDeviceService;
    public HeartBeatServerHandler(ISysDeviceService sysDeviceService){
        this.sysDeviceService = sysDeviceService;
    }

    /**
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     * channel 通道 action 活跃的
     * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println(incoming.remoteAddress() + " active!");
        String deviceNo = "1F 00 00 01";
        ClientChannelUtil2.connectServerChannel(incoming, deviceNo, sysDeviceService.getDeviceControl(deviceNo)); //试衣间1，先固定写
        super.channelActive(ctx);
    }

    /**
     * channel 通道 Inactive 不活跃的
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel leaving = ctx.channel();
        System.out.println(leaving.remoteAddress() + " inactive!");
        channels.remove(leaving);
        ClientChannelUtil2.clearServerChannel(leaving);
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            System.out.println("server channelRead.." + msg);
            String message = getMessage((ByteBuf) msg);
            System.out.println(ctx.channel().remoteAddress() + "->Server :" + message);
            String[] strAryHex = message.split(" ");
            if(message.contains("AA CA FF")){ //心跳：A5 5A 1F 00 00 01 AA CA FF
                ctx.writeAndFlush(Unpooled.copiedBuffer(StringTool.stringArrayToByteArray(message.split(" "), strAryHex.length)));
            }else{
                ClientBean clientBean = ClientChannelUtil2.getClientBeanByMsg(message);
                String comm = strAryHex[6];
                switch (comm){ //读取某个试衣间所有硬件状态
                    case "DC":
                        for(int i = 7; i < strAryHex.length-2;){
                            String hex = strAryHex[i];
                            if("DA".equals(hex)){       //大门(0)
                                clientBean.getDeviceControl(0).setReadStatus(StrUtil.objToStr(Integer.parseInt(strAryHex[i + 1])));
                                i=i+2;
                            }else if("DB".equals(hex)){ //柜子(1-8)
                                clientBean.getDeviceControl(Integer.parseInt(strAryHex[i+1])).setReadStatus(StrUtil.objToStr(Integer.parseInt(strAryHex[i + 2])));
                                i=i+3;
                            }
                        }
                        break;
                    case "DA": //开关大门
                        clientBean.getDeviceControl(0).setReadStatus(StrUtil.objToStr(Integer.parseInt(strAryHex[7])));
                        break;
                    case "DB": //开关柜子
                        clientBean.getDeviceControl(Integer.parseInt(strAryHex[7])).setReadStatus(StrUtil.objToStr(Integer.parseInt(strAryHex[8])));
                        break;
                    case "DD": //读取当前射频标签数量，硬件返回:0x05 RFID1 RFID2 …RFID5,  0x05:RFID数量，RFID1-5：RFID标签的值。
                        List<String> rfids = new ArrayList<>();
                        StringBuilder rfid = new StringBuilder();
                        int rfisSize = 0;
                        for(int i = 8; i < strAryHex.length-2;i++){
                            if(rfid.length() > 0) rfid.append(" ");
                            rfid.append(strAryHex[i]);
                            rfisSize++;
                            if(rfisSize == 12){ //rfid标签码 12长度一个
                                rfids.add(rfid.toString());
                                rfid.setLength(0);
                                rfisSize = 0;
                            }
                        }
                        clientBean.setRfidDatas(rfids);
                        break;
                }
                if(clientBean != null) {
                    clientBean.setReadStatus(IDBConstant.LOGIC_STATUS_YES);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    //待解决===channelActive====handlerAdded
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端handlerAdded");
        Channel incoming = ctx.channel();
        channels.add(incoming);
        /*System.out.println("cs：" + channels.size());
        for (Channel channel : channels){
            System.out.println("服务端handlerAdded：" + incoming.remoteAddress());
            channel.writeAndFlush(sendMsg("服务端说：欢迎[" + incoming.remoteAddress() + "]加入，当前总连接数：" + channels.size() + "\n"));
        }
        incoming.writeAndFlush(sendMsg("欢迎来到" + InetAddress.getLocalHost().getHostName() + " service!"));*/
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端handlerRemoved");
        Channel leaving = ctx.channel();
        channels.remove(leaving);
        ClientChannelUtil2.clearServerChannel(leaving);
        /*for (Channel channel : channels){
            System.out.println("handlerRemoved：" + leaving.remoteAddress());
            channel.writeAndFlush(sendMsg("服务端说：[" + leaving.remoteAddress() + "]离开，当前总连接数：" + channels.size()));
        }*/
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

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
        System.out.println(ctx.channel().remoteAddress() + "发生异常，退出，当前总连接数：" + channels.size());
        ClientChannelUtil2.clearServerChannel(ctx.channel());
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

    private ByteBuf sendMsg(String sendInfo){
        return Unpooled.copiedBuffer(sendInfo, CharsetUtil.UTF_8);
    }

}
