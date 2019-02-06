/*
package com.wardrobe.platform.netty.client;

import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.SysDeviceControl;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.netty.client.bean.ClientBean;
import com.wardrobe.platform.netty.client.bean.DeviceBean;
import com.wardrobe.platform.netty.reconnect.client.HeartBeatClientHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by cxs on 2018/9/9.
 * 获取方法不加同步锁，修改或删除方法加，避免死锁
 *//*

public class ClientChannelUtil {

    private static Logger logger = Logger.getLogger(ClientChannelUtil.class);

    public static int STATUS_NOT_CONNECT = 0;
    public static int STATUS_CONNECT_ING = 1;
    public static int STATUS_RECONNECT = 2;

    public static String PULSE_OPEN = "P";
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
            logger.info("r:" + clientBeans.size() + ":" + clientBean.getPort() + ":" + clientBean.getStatus());
        }
    }

    public synchronized static void clearServerChannel(Channel channel) { //   /192.168.207.156:9900
        if(channel == null) return;
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

    public synchronized static void connectServerChannel(Channel channel, List<SysDeviceControl> deviceControls) {
        try{
            if(channel == null) return;
            ClientBean clientBean = getClientBean(channel);
            if(clientBean == null){
                InetSocketAddress socketAddress = (InetSocketAddress)channel.remoteAddress();
                clientBean = new ClientBean(deviceControls);
                clientBean.setHost(socketAddress.getHostString());
                clientBean.setPort(socketAddress.getPort());
                clientBean.setServiceChannel(channel);
                clientBean.setStatus(STATUS_CONNECT_ING);
                clientBeans.add(clientBean);
            }else {
                clientBean.setServiceChannel(channel);
                clientBean.setStatus(STATUS_CONNECT_ING);
            }
            logger.info("c:" + clientBeans.size() + ":" + clientBean.getPort() + ":" + clientBean.getStatus());
        }catch (Exception e){
            e.printStackTrace();
            throw new MessageException();
        }
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

    public static String getNowStatusName(String ip, int port){
        ClientBean clientBean = getClientBean(ip, port);
        if(clientBean != null){
            if(STATUS_CONNECT_ING == clientBean.getStatus()) return "连接中";
            if(STATUS_RECONNECT == clientBean.getStatus()) return "正在重连";
        }
        return "未连接";
    }

    public static String getNowStatus(String ip, int port){
        ClientBean clientBean = getClientBean(ip, port);
        return clientBean != null && STATUS_CONNECT_ING == clientBean.getReadStatus() ? IDBConstant.LOGIC_STATUS_YES : IDBConstant.LOGIC_STATUS_NO;
    }

    public static boolean isOpen(String ip, int port){
        ClientBean clientBean = getClientBean(ip, port);
        return clientBean != null && clientBean.getStatus() == STATUS_CONNECT_ING;
    }

    public static SysDeviceControl getDeviceBean(String ip, int port, int deviceNo){
        ClientBean clientBean = getClientBean(ip, port);
        if(clientBean != null){
            return clientBean.getDeviceControl(deviceNo);
        }
        return null;
    }

    public static SysDeviceControl getDeviceBean(Channel channel, int deviceNo){
        ClientBean clientBean = getClientBean(channel);
        if(clientBean != null){
            return clientBean.getDeviceControl(deviceNo);
        }
        return null;
    }

    public static SysDeviceControl readDriveStatus(String ip, int port, int deviceNo){
        return readDriveStatus(getServerChannel(ip, port), deviceNo);
    }

    public static SysDeviceControl readDriveStatus(Channel serverChannel, int deviceNo){
        SysDeviceControl deviceBean = getDeviceBean(serverChannel, deviceNo);
        synchronized (deviceBean) {
            deviceBean.setStatus(null); //获取中

            serverChannel.writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.READ_STATUS + deviceNo, CharsetUtil.UTF_8));

            logger.info("serverChannel：" + serverChannel);
            long start = System.currentTimeMillis();
            while ((System.currentTimeMillis()-start) <= 3000 && deviceBean.getStatus() == null) { //3秒内轮询等待TCP消息返回
                try {
                    logger.info("wait...~" + clientBeans.size());
                    for(ClientBean clientBean : clientBeans){
                        logger.info(clientBean.getHost() + ":" + clientBean.getPort());
                        logger.info("=====================================");
                        logger.info("isActive：" + clientBean.getServiceChannel().isActive());
                        logger.info("iaOpen：" + clientBean.getServiceChannel().isOpen());
                        logger.info("isWritable：" + clientBean.getServiceChannel().isWritable());
                        logger.info("=====================================");
                    }
                    Thread.sleep(200L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            logger.info("wait success..." + deviceBean.getStatus());
            return deviceBean;
        }
    }

}
*/
