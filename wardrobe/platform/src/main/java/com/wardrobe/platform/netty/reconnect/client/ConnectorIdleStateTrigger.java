package com.wardrobe.platform.netty.reconnect.client;

import com.wardrobe.platform.rfid.util.StringTool;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;

/**
 * 心跳检测状态处理类
 * Unpooled：未合并的
 * HeartBeat：心跳
 */
@ChannelHandler.Sharable
public class ConnectorIdleStateTrigger extends ChannelInboundHandlerAdapter {

    private Logger logger = Logger.getLogger(ConnectorIdleStateTrigger.class);

    //心跳序列
    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Heartbeat", CharsetUtil.UTF_8)); //不释放资源，读取后

    //用户事件触发[服务端未使用，自动重连类的功能有效]
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        logger.info("[client]: xi tiao jian ce!!!");
        System.out.println("[client]: xi tiao jian ce!!!");
        if(evt instanceof IdleStateEvent){
            IdleState state = ((IdleStateEvent) evt).state();
            if(state == IdleState.WRITER_IDLE) { /* 4秒发送一次心跳数据 */
                String[] strAryHex = {"A5", "5A", "1F" ,"00" ,"00" ,"01" ,"AA" ,"CA" ,"FF"};
                ctx.writeAndFlush(Unpooled.copiedBuffer(StringTool.stringArrayToByteArray(strAryHex, strAryHex.length)));
                //ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());
            }
        }else{
            super.userEventTriggered(ctx, evt);
        }
    }
}





























