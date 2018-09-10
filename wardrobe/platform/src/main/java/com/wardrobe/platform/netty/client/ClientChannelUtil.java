package com.wardrobe.platform.netty.client;

import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.netty.client.bean.ClientBean;
import io.netty.channel.Channel;
import sun.security.pkcs11.P11Util;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxs on 2018/9/9.
 */
public class ClientChannelUtil {

    public static int STATUS_NOT_CONNECT = 0;
    public static int STATUS_CONNECT_ING = 1;
    public static int STATUS_RECONNECT = 2;

    public static String LOCK_OPEN = "L";
    public static String LOCK_CLOSE = "D";
    public static String LOCK_OPEN_ALL = "LA";
    public static String LOCK_CLOSE_ALL = "DA";

    private static List<ClientBean> clientBeans = new ArrayList<>();

    public synchronized static void removeServerChannel(Channel channel) { //   /192.168.207.156:9900
        InetSocketAddress socketAddress = (InetSocketAddress)channel.remoteAddress();
        ClientBean clientBean = getClientBean(socketAddress.getHostName(), socketAddress.getPort());
        if(clientBean != null){
            clientBean.setStatus(STATUS_RECONNECT);
        }
        System.out.println("r:" + clientBeans.size() + ":" + clientBean.getPort()  + ":" +  clientBean.getStatus());
    }

    public synchronized static Channel getServerChannel(String ip, int port){
        ClientBean clientBean = getClientBean(ip, port);
        if(clientBean != null) return clientBean.getServiceChannel();
        return null;
    }

    public synchronized static void connectServerChannel(Channel channel) {
        ClientBean clientBean = getClientBean(channel);
        if(clientBean == null){
            InetSocketAddress socketAddress = (InetSocketAddress)channel.remoteAddress();
            clientBean = new ClientBean();
            clientBean.setHost(socketAddress.getHostName());
            clientBean.setPort(socketAddress.getPort());
            clientBean.setServiceChannel(channel);
            clientBean.setStatus(STATUS_CONNECT_ING);
            clientBeans.add(clientBean);
        }else {
            clientBean.setStatus(STATUS_CONNECT_ING);
        }
        System.out.println("c:" + clientBeans.size() + ":" + clientBean.getPort()  + ":" + clientBean.getStatus());
    }

    public synchronized static ClientBean getClientBean(String ip, int port){
        for(ClientBean clientBean : clientBeans){
            if(ip.equals(clientBean.getHost()) && port == clientBean.getPort()){
                return clientBean;
            }
        }
        return null;
    }

    private synchronized static ClientBean getClientBean(Channel channel){
        InetSocketAddress socketAddress = (InetSocketAddress)channel.remoteAddress();
        return getClientBean(socketAddress.getHostName(), socketAddress.getPort());
    }

    public synchronized static String getNowStatus(String ip, int port){
        ClientBean clientBean = getClientBean(ip, port);
        if(clientBean != null){
            if(STATUS_CONNECT_ING == clientBean.getStatus()) return "连接中";
            if(STATUS_RECONNECT == clientBean.getStatus()) return "正在重连";
        }
        return "未连接";
    }

    public synchronized static boolean isOpen(String ip, int port){
        ClientBean clientBean = getClientBean(ip, port);
        return clientBean != null && clientBean.getStatus() == STATUS_CONNECT_ING;
    }

}
