package com.wardrobe.platform.netty.reconnect.server;

import com.wardrobe.common.util.DateUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.log4j.Logger;

import java.util.Date;


/**
 * 心跳检测状态处理类
 */
@ChannelHandler.Sharable
public class AcceptorIdleStateTrigger extends ChannelInboundHandlerAdapter {

    Logger logger = Logger.getLogger(AcceptorIdleStateTrigger.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("server:userEventTriggered");
        if(evt instanceof IdleStateEvent){
            IdleState state = ((IdleStateEvent) evt).state();
            if(state == IdleState.READER_IDLE){ /*读超时*/
                logger.warn(DateUtil.dateToString(new Date(), DateUtil.YYYYMMDDHHMMSS) + "idle exception:" + ctx.channel());
                throw new Exception("idle exception");
            }
            super.userEventTriggered(ctx, evt);
        }
    }
}
