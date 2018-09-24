package com.wardrobe.platform.service;

import com.wardrobe.common.bean.UserDriveBean;
import com.wardrobe.common.po.SysDeviceInfo;

import java.util.Map;

/**
 * Created by cxs on 2018/9/10.
 */
public interface IRelayService {

    SysDeviceInfo getSysDeviceInfo(int did);

    boolean connectServer(String ip, int port) throws Exception;

    void openServerLock(String ip, int port, int lockId) throws Exception;

    void closeServerLock(String ip, int port, int lockId);

    void openServerAllLock(String ip, int port);

    void closeServerAllLock(String ip, int port);

    void openServerDrive(String ip, int port, int driveId);

    void closeServerDrive(String ip, int port, int driveId);

    Map<String, Object> getRealyIndexsIn();

    void openDoor(int did, int uid);

    void closeDoor(int did, int uid);

    void openLock(int dcid) throws Exception;

    void closeLock(int dcid) throws Exception;

    void downlineRelay(String ip, int port);

    void saveUserOpenServerDrive(UserDriveBean userDriveBean) throws Exception;

    void saveUserOpenServerLock(UserDriveBean userDriveBean) throws Exception;

    void saveUserCloseServerLock(UserDriveBean userDriveBean) throws Exception;

    void saveUserCloseServerDrive(UserDriveBean userDriveBean) throws Exception;

}
