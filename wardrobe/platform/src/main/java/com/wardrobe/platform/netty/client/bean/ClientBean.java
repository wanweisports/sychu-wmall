package com.wardrobe.platform.netty.client.bean;

import io.netty.channel.Channel;

/**
 * Created by cxs on 2018/9/10.
 */
public class ClientBean {

    private int status = 0; //0：未连接  1：已连接  2：正在重连
    private String host;
    private int port;
    private Channel serviceChannel;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Channel getServiceChannel() {
        return serviceChannel;
    }

    public void setServiceChannel(Channel serviceChannel) {
        this.serviceChannel = serviceChannel;
    }
}
