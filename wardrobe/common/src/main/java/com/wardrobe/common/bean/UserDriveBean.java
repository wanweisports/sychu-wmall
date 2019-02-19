package com.wardrobe.common.bean;

import com.wardrobe.common.po.SysDeviceInfo;
import com.wardrobe.common.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 陈小松 on 2018/9/24.
 */
public class UserDriveBean {

    private int uid;
    private int roid;
    private int did;
    private int lockId;
    private SysDeviceInfo sysDeviceInfo;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRoid() {
        return roid;
    }

    public void setRoid(int roid) {
        this.roid = roid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getLockId() {
        return lockId;
    }

    public void setLockId(int lockId) {
        this.lockId = lockId;
    }

    public SysDeviceInfo getSysDeviceInfo() {
        return sysDeviceInfo;
    }

    public void setSysDeviceInfo(SysDeviceInfo sysDeviceInfo) {
        this.sysDeviceInfo = sysDeviceInfo;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(DateUtil.dateToString(DateUtil.addHHMMTime(new Date(), Calendar.MINUTE, -15), DateUtil.YYYYMMDDHHMMSS));
    }
}
