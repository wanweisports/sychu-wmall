package com.wardrobe.controller.init;

import com.wardrobe.platform.netty.server.NettyServer;
import com.wardrobe.platform.service.ISysDeviceService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cxs on 2019/2/3.
 */
@Component
public class InitializingCache implements InitializingBean {

    @Autowired
    private ISysDeviceService deviceService;

    @Override
    public void afterPropertiesSet() throws Exception {
        NettyServer nettyServer = new NettyServer();
        nettyServer.serverStat(deviceService);
    }
}
