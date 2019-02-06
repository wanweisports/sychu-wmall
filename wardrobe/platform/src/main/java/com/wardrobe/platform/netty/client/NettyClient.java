package com.wardrobe.platform.netty.client;

import com.wardrobe.platform.rfid.util.StringTool;
import com.wardrobe.platform.service.ISysDeviceService;
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
    private ISysDeviceService deviceService;
    public EventLoopGroup clientGroup = new NioEventLoopGroup();

    public NettyClient(String host, int port, ISysDeviceService deviceService) {
        this.host = host;
        this.port = port;
        this.deviceService = deviceService;
    }

    public ChannelFuture clientServer() throws Exception{
        //try {
            Bootstrap boot = new Bootstrap();
            boot.group(clientGroup);
            boot.channel(NioSocketChannel.class); //使用NioSocketChannel来作为连接用的channel类
            boot.handler(new LoggingHandler(LogLevel.INFO));
            //boot.remoteAddress(new InetSocketAddress(this.host, this.port)); //绑定连接端口和host信息
            boot.handler(new ClientHandlerInit(host, port, boot, deviceService));

            //进行连接
            ChannelFuture future = boot.connect(host, port).sync();
            return future;

            /*try {
                ChannelFuture future = boot.connect(host, port).sync();
                //future.channel().closeFuture().sync(); // 异步等待关闭连接channel（服务器断开，则不会阻塞了，代码往下走，线程结束则次客户端结束）
                return future;
            }catch (Throwable t){
                throw new Exception("connects to  fails", t);
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            *//*System.out.println(77777777777777777L);
            clientGroup.shutdownGracefully().sync();*//*
        }*/
    }

    public static void main(String[] args) throws Exception{
        /*new Thread(){
            @Override
            public void run() {
                NettyClient nettyClient = new NettyClient("192.168.1.166", 1234);
                try {
                    nettyClient.clientServer();
                }catch (Exception e){e.printStackTrace();}
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                NettyClient nettyClient = new NettyClient("192.168.1.168", 1234);
                try {
                    nettyClient.clientServer();
                }catch (Exception e){e.printStackTrace();}
            }
        }.start();*/

        NettyClient nettyClient = new NettyClient("127.0.0.1", 9900, null);
        //NettyClient nettyClient = new NettyClient("server.natappfree.cc", 32911, null);
        try {
            nettyClient.clientServer().channel().closeFuture().sync();
        }catch (Exception e){e.printStackTrace();}
    }

}
















