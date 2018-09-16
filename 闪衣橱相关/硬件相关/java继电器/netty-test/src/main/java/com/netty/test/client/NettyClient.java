package com.netty.test.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
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
                ChannelFuture future = boot.connect(host, port).sync();

                // 以下代码在synchronized同步块外面是安全的

                Scanner scanner = new Scanner(System.in);
                System.out.println("请输入控制指令：");
                while (true) {
                    String op = scanner.nextLine();
                    System.out.println(op);
                    ClientHandler.channels.get(0).writeAndFlush(Unpooled.copiedBuffer(op, CharsetUtil.UTF_8));
                }
                //future.channel().closeFuture().sync(); // 异步等待关闭连接channel（服务器断开，则不会阻塞了，代码往下走，线程结束则次客户端结束）
                //Thread.sleep(10000000);
            }catch (Throwable t){
                throw new Exception("connects to  fails", t);
            }
        }finally {
            System.out.println(6666666666666666666L);
            clientGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception{
        NettyClient nettyClient = new NettyClient("127.0.0.1", 9900);
        nettyClient.clientServer();
    }

}
















