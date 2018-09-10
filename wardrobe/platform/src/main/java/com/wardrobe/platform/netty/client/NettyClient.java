package com.wardrobe.platform.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by 雷达 on 2018/7/12.
 */
public class NettyClient {

    private String host;
    private int port;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void clientServer() throws Exception{
        EventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            Bootstrap boot = new Bootstrap();
            boot.group(clientGroup);
            boot.channel(NioSocketChannel.class); //使用NioSocketChannel来作为连接用的channel类
            boot.handler(new LoggingHandler(LogLevel.INFO));
            //boot.remoteAddress(new InetSocketAddress(this.host, this.port)); //绑定连接端口和host信息
            boot.handler(new ClientHandlerInit(host, port, boot));

            //进行连接
            try {
                ChannelFuture future = boot.connect(host, port);
                while (true){}
            }catch (Throwable t){
                throw new Exception("connects to  fails", t);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println(77777777777777777L);
            clientGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception{
        new Thread(){
            @Override
            public void run() {
                NettyClient nettyClient = new NettyClient("192.168.207.156", 9900);
                try {
                    nettyClient.clientServer();
                }catch (Exception e){e.printStackTrace();}
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                NettyClient nettyClient = new NettyClient("192.168.207.156", 9901);
                try {
                    nettyClient.clientServer();
                }catch (Exception e){e.printStackTrace();}
            }
        }.start();
    }

}
















