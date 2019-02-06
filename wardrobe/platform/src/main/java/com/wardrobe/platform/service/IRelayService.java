package com.wardrobe.platform.service;

import com.wardrobe.common.bean.UserDriveBean;
import com.wardrobe.common.po.SysDeviceControl;
import com.wardrobe.common.po.SysDeviceInfo;

import java.util.Map;

/**
 * Created by cxs on 2018/9/10.
 */
public interface IRelayService {

    SysDeviceInfo getSysDeviceInfo(int did);

    SysDeviceControl getSysDeviceControl(int dcid);

/*    boolean connectServer(String ip, int port) throws Exception;*/

/*    void openServerLock(int did, int lockId) throws Exception;

    void closeServerLock(int did, int lockId);*/

/*    void openServerAllLock(String ip, int port);

    void closeServerAllLock(String ip, int port);*/

/*    void openServerDrive(int did);

    void closeServerDrive(int did);*/

/*    Map<String, Object> getRealyIndexsIn();*/

    void openDoor(int did, int uid);

/*    void closeDoor(int did, int uid);

    void openLock(int dcid) throws Exception;

    void closeLock(int dcid) throws Exception;*/

/*    void downlineRelay(String ip, int port);*/

    void saveUserOpenServerDrive(UserDriveBean userDriveBean) throws Exception;

    void saveUserOpenServerLock(UserDriveBean userDriveBean) throws Exception;

    void saveUserCloseServerLock(UserDriveBean userDriveBean) throws Exception;

    void saveUserCloseServerDrive(UserDriveBean userDriveBean) throws Exception;

    Map<String, Object> openDoor(int did);

    Map<String, Object> closeDoor(int did);

    Map<String, Object> openLock(int did, int lock);

    Map<String, Object> closeLock(int did, int lock);

    Map<String, Object> readAll(int did);

    Map<String, Object> rfidRead(int did);

    Map<String, Object> readEpcLabelApi(int did);

}
