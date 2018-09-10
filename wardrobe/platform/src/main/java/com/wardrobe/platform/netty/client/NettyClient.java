package com.wardrobe.platform.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

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
                // 以下代码在synchronized同步块外面是安全的

                Scanner scanner = new Scanner(System.in);
                System.out.println("请输入控制指令：");
                while (true) {
                    String op = scanner.nextLine();

                    System.out.println(op + ":" + ClientChannelUtil.getChannels().size());
                    //Channel relayChannel = ClientChannelUtil.getFristChannel();
                    Channel relayChannel = ClientChannelUtil.getRelayChannel("192.168.1.168", 1234);
                    if(relayChannel == null){
                        System.out.println("继电器未连接！");
                        continue;
                    }
                    relayChannel.writeAndFlush(Unpooled.copiedBuffer(op, CharsetUtil.UTF_8));
                }

                //Thread.sleep(10000000);
            }catch (Throwable t){
                throw new Exception("connects to  fails", t);
            }
        }finally {
            System.out.println(77777777777777777L);
            clientGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception{
        NettyClient nettyClient = new NettyClient("192.168.1.168", 1234);
        nettyClient.clientServer();
    }

}
















