package com.wardrobe.platform.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * Created by 雷达 on 2018/7/12.
 */
public class NettyServer {

    private int port = 9900;

    public void serverStat() throws InterruptedException{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.handler(new LoggingHandler(LogLevel.INFO));
            b.localAddress(new InetSocketAddress(port));
            b.childHandler(new ServerHandlerInit()); //handler在初始化时就会执行，而childHandler会在客户端成功connect后才执行，这是两者的区别。
            b.option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

            //服务器绑定端口监听
            ChannelFuture f = b.bind(port).sync();
            System.out.println(NettyServer.class + " 启动正在监听： " + f.channel().localAddress());

            //服务器关闭端口监听
            f.channel().closeFuture().sync();

            System.out.println("########################################");
        }finally {
            bossGroup.shutdownGracefully().sync();  //释放线程池资源
            workerGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        NettyServer nettyServer = new NettyServer();
        nettyServer.serverStat();
    }

}












