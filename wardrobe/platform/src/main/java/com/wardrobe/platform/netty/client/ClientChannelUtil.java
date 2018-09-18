package com.wardrobe.platform.netty.client;

import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.netty.client.bean.ClientBean;
import com.wardrobe.platform.netty.client.bean.DeviceBean;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxs on 2018/9/9.
 * 获取方法不加同步锁，修改或删除方法加，避免死锁
 */
public class ClientChannelUtil {

    public static int STATUS_NOT_CONNECT = 0;
    public static int STATUS_CONNECT_ING = 1;
    public static int STATUS_RECONNECT = 2;

    public static String LOCK_OPEN = "L";
    public static String LOCK_CLOSE = "D";
    public static String LOCK_OPEN_ALL = "LA";
    public static String LOCK_CLOSE_ALL = "DA";
    public static String READ_STATUS = "R";

    public static String READ_OPEN = "Relayon";
    public static String READ_CLOSE = "Relayoff";

    private static List<ClientBean> clientBeans = new ArrayList<>(); //1.166 AND 1.168 [大门 AND 锁]

    public synchronized static void removeServerChannel(Channel channel) { //   /192.168.207.156:9900
        InetSocketAddress socketAddress = (InetSocketAddress)channel.remoteAddress();
        if(socketAddress != null) {
            ClientBean clientBean = getClientBean(socketAddress.getHostString(), socketAddress.getPort());
            if (clientBean != null) {
                clientBean.setStatus(STATUS_RECONNECT);
            }
            System.out.println("r:" + clientBeans.size() + ":" + clientBean.getPort()  + ":" +  clientBean.getStatus());
        }
    }

    public synchronized static void clearServerChannel(Channel channel) { //   /192.168.207.156:9900
        InetSocketAddress socketAddress = (InetSocketAddress)channel.remoteAddress();
        if(socketAddress != null) {
            ClientBean clientBean = getClientBean(socketAddress.getHostString(), socketAddress.getPort());
            if (clientBean != null) {
                clientBeans.remove(clientBean);
            }
        }
    }

    public static Channel getServerChannel(String ip, int port){
        ClientBean clientBean = getClientBean(ip, port);
        if(clientBean != null) return clientBean.getServiceChannel();
        return null;
    }

    public synchronized static void connectServerChannel(Channel channel) {
        ClientBean clientBean = getClientBean(channel);
        if(clientBean == null){
            InetSocketAddress socketAddress = (InetSocketAddress)channel.remoteAddress();
            clientBean = new ClientBean();
            clientBean.setHost(socketAddress.getHostString());
            clientBean.setPort(socketAddress.getPort());
            clientBean.setServiceChannel(channel);
            clientBean.setStatus(STATUS_CONNECT_ING);
            clientBeans.add(clientBean);
        }else {
            clientBean.setServiceChannel(channel);
            clientBean.setStatus(STATUS_CONNECT_ING);
        }
        System.out.println("c:" + clientBeans.size() + ":" + clientBean.getPort()  + ":" + clientBean.getStatus());
    }

    public static ClientBean getClientBean(String ip, int port){
        for(ClientBean clientBean : clientBeans){
            if(ip.equals(clientBean.getHost()) && port == clientBean.getPort()){
                return clientBean;
            }
        }
        return null;
    }

    public static ClientBean getClientBean(Channel channel){
        InetSocketAddress socketAddress = (InetSocketAddress)channel.remoteAddress();
        return getClientBean(socketAddress.getHostString(), socketAddress.getPort());
    }

    public static String getNowStatus(String ip, int port){
        ClientBean clientBean = getClientBean(ip, port);
        if(clientBean != null){
            if(STATUS_CONNECT_ING == clientBean.getStatus()) return "连接中";
            if(STATUS_RECONNECT == clientBean.getStatus()) return "正在重连";
        }
        return "未连接";
    }

    public static boolean isOpen(String ip, int port){
        ClientBean clientBean = getClientBean(ip, port);
        return clientBean != null && clientBean.getStatus() == STATUS_CONNECT_ING;
    }

    public static DeviceBean getDeviceBean(String ip, int port, int deviceNo){
        ClientBean clientBean = getClientBean(ip, port);
        if(clientBean != null){
            return clientBean.getDeviceBeans().get(deviceNo-1);
        }
        return null;
    }

    public static DeviceBean getDeviceBean(Channel channel, int deviceNo){
        ClientBean clientBean = getClientBean(channel);
        if(clientBean != null){
            return clientBean.getDeviceBeans().get(deviceNo-1);
        }
        return null;
    }

    public static DeviceBean readDriveStatus(Channel serverChannel, int deviceNo){
        DeviceBean deviceBean = getDeviceBean(serverChannel, deviceNo);
        synchronized (deviceBean) {
            deviceBean.setStatus(null); //获取中

            serverChannel.writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.READ_STATUS + deviceNo, CharsetUtil.UTF_8));

            System.out.println("serverChannel：" + serverChannel);
            long start = System.currentTimeMillis();
            while ((System.currentTimeMillis()-start) <= 5000 && deviceBean.getStatus() == null) { //5秒内轮询等待TCP消息返回
                try {
                    System.out.println("等待中...");
                    Thread.sleep(200L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("等待完毕..." + deviceBean.getStatus());
            return deviceBean;
        }
    }

}
