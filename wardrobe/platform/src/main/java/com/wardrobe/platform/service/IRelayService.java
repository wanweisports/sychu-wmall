package com.wardrobe.platform.service;

import java.util.Map;

/**
 * Created by cxs on 2018/9/10.
 */
public interface IRelayService {

    boolean connectServer(String ip, int port) throws Exception;

    void openServerLock(String ip, int port, int lockId) throws Exception;

    void closeServerLock(String ip, int port, int lockId);

    void openServerAllLock(String ip, int port);

    void closeServerAllLock(String ip, int port);

    void openServerDrive(String ip, int port, int driveId);

    void closeServerDrive(String ip, int port, int driveId);

    Map<String, Object> getRealyIndexsIn();

}
