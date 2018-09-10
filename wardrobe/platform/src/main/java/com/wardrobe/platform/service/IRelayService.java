package com.wardrobe.platform.service;

/**
 * Created by cxs on 2018/9/10.
 */
public interface IRelayService {

    void connectServer(String ip, int port) throws Exception;

    void openServerLock(String ip, int port, int lockId);

    void closeServerLock(String ip, int port, int lockId);

}
