package com.wardrobe.platform.netty.client;

import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.SysDeviceControl;
import com.wardrobe.platform.netty.client.bean.ClientBean;
import com.wardrobe.platform.rfid.util.StringTool;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cxs on 2019/1/22.
 */
public class ClientChannelUtil2 {

    public static int STATUS_CONNECT_ING = 1;

    private static Logger logger = Logger.getLogger(ClientChannelUtil2.class);

    private static List<ClientBean> clientBeans = new ArrayList<>();

    //private static ExecutorService es = Executors.newFixedThreadPool(1);

    public static void connectServerChannel(Channel channel, String deviceNo, List<SysDeviceControl> deviceControls) {
        try{
            if(channel == null) return;
            clientBeans.clear(); //只有一个连接

            InetSocketAddress socketAddress = (InetSocketAddress)channel.remoteAddress();
            ClientBean clientBean = new ClientBean(deviceControls);
            clientBean.setDid(deviceControls.get(0).getDid());
            clientBean.setDeviceNo(deviceNo);
            clientBean.setHost(socketAddress.getHostString());
            clientBean.setPort(socketAddress.getPort());
            clientBean.setServiceChannel(channel);
            clientBean.setConnDate(new Date());
            clientBeans.add(clientBean);

            logger.info("c:" + clientBeans.size() + ":" + clientBean.getPort());
        }catch (Exception e){
            e.printStackTrace();
            throw new MessageException();
        }
    }

    public static void clearServerChannel(Channel channel) {
        if(channel == null) return;
        clientBeans.clear(); //只有一个连接
    }

    public synchronized static boolean isOpen(){
        return clientBeans.size() > 0;
    }

    public synchronized static void isOpenThrowable(){
        if(!isOpen()) throw new MessageException("硬件未连接，请稍候再试！");
    }

    public static ClientBean getClientBean(int did){
        for(ClientBean clientBean : clientBeans){
            if(clientBean.getDid() == did) return clientBean;
        }
        return null;
    }

    public static ClientBean getClientBean(String deviceNo){
        for(ClientBean clientBean : clientBeans){
            if(clientBean.getDeviceNo().equals(deviceNo)) return clientBean;
        }
        return null;
    }

    public static String getDeviceNo(String message){
        String[] comms = message.split(" ");
        return new StringBuilder().append(comms[2]).append(" ").append(comms[3]).append(" ").append(comms[4]).append(" ").append(comms[5]).toString();
    }

    public static ClientBean getClientBeanByMsg(String message){
        return getClientBean(getDeviceNo(message));
    }

    public synchronized static void sendEventWait(ClientBean clientBean, String[] comms){
        clientBean.setReadStatus(null); //获取中
        sendEvent(clientBean, comms);
        readAllStatus(clientBean);
    }

    public synchronized static void sendEvent(ClientBean clientBean, String[] comms){
        List<String> sendComm = new ArrayList<>();
        sendComm.add("A5");
        sendComm.add("5A");
        int checkSum = 0;
        String[] deviceComms = clientBean.getDeviceNo().split(" ");
        for(String deviceComm : deviceComms){
            checkSum += Integer.parseInt(deviceComm, 16);
            sendComm.add(deviceComm);
        }
        for(String comm : comms){
            checkSum += Integer.parseInt(comm, 16);
            sendComm.add(comm);
        }
        sendComm.add(Integer.toHexString(checkSum).toUpperCase());
        sendComm.add("FF");
        clientBean.getServiceChannel().writeAndFlush(Unpooled.copiedBuffer(StringTool.stringArrayToByteArray(sendComm.toArray(new String[sendComm.size()]), sendComm.size())));
        /*es.execute(() -> {
            clientBean.getServiceChannel().writeAndFlush(Unpooled.copiedBuffer(StringTool.stringArrayToByteArray(sendComm.toArray(new String[sendComm.size()]), sendComm.size())));
        });*/
    }

    public static void readAllStatus(ClientBean clientBean){
        synchronized (clientBean) {
            //serverChannel.writeAndFlush(Unpooled.copiedBuffer(ClientChannelUtil.READ_STATUS + deviceNo, CharsetUtil.UTF_8));

            logger.info("serverChannel：" + clientBean.getServiceChannel());
            long start = System.currentTimeMillis();
            while ((System.currentTimeMillis()-start) <= 3000 && clientBean.getReadStatus() == null) { //3秒内轮询等待TCP消息返回
                try {
                    logger.info("wait...~" + clientBeans.size() + "-connect size.");
                    System.out.println(clientBean.getReadStatus());
                    for(ClientBean cb : clientBeans){
                        logger.info(cb.getHost() + ":" + cb.getPort());
                        logger.info("=====================================");
                        logger.info("isActive：" + cb.getServiceChannel().isActive());
                        logger.info("isOpen：" + cb.getServiceChannel().isOpen());
                        logger.info("isWritable：" + cb.getServiceChannel().isWritable());
                        logger.info("=====================================");
                    }
                    Thread.sleep(200L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            logger.info("wait success..." + clientBean.getReadStatus());
        }
    }

}
