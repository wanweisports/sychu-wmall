package com.wardrobe.platform.netty.client;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈小松 on 2018/9/9.
 */
public class ClientChannelUtil {

    private static List<Channel> channels = new ArrayList<Channel>();

    public static List<Channel> getChannels() {
        return channels;
    }

    public static void addChannel(Channel channel) {
        if(!channels.contains(channel)){
            channels.add(channel);
        }
    }

    public static void removeChannel(Channel channel) {
        channels.remove(channel);
    }

    public static Channel getFristChannel(){
        return channels.size() > 0 ? channels.get(0) : null;
    }

}
