package com.wardrobe.platform.netty.reconnect.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 心跳检测状态处理类
 */
@ChannelHandler.Sharable
public class AcceptorIdleStateTrigger extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("server:userEventTriggered");
        if(evt instanceof IdleStateEvent){
            IdleState state = ((IdleStateEvent) evt).state();
            if(state == IdleState.READER_IDLE){
                throw new Exception("idle exception");
            }
            super.userEventTriggered(ctx, evt);
        }
    }
}
