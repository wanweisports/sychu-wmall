package com.wardrobe.common.po;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "sys_device_info", schema = "")
public class SysDeviceInfo {
    private Integer did;
    private String name;
    private String areaId;
    private String address;
    private String status;
    private Timestamp createTime;
    private String startTime;
    private String endTime;
    private String doorIp;
    private Integer doorPort;
    private String lockIp;
    private Integer lockPort;
    private Timestamp openTime;
    private Timestamp closeTime;
    private Timestamp openLockTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "did")
    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "areaId")
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysDeviceInfo that = (SysDeviceInfo) o;

        if (did != that.did) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (areaId != null ? !areaId.equals(that.areaId) : that.areaId != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = did;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (areaId != null ? areaId.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "startTime")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "endTime")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "doorIp")
    public String getDoorIp() {
        return doorIp;
    }

    public void setDoorIp(String doorIp) {
        this.doorIp = doorIp;
    }

    @Basic
    @Column(name = "doorPort")
    public Integer getDoorPort() {
        return doorPort;
    }

    public void setDoorPort(Integer doorPort) {
        this.doorPort = doorPort;
    }

    @Basic
    @Column(name = "lockIp")
    public String getLockIp() {
        return lockIp;
    }

    public void setLockIp(String lockIp) {
        this.lockIp = lockIp;
    }

    @Basic
    @Column(name = "lockPort")
    public Integer getLockPort() {
        return lockPort;
    }

    public void setLockPort(Integer lockPort) {
        this.lockPort = lockPort;
    }

    @Basic
    @Column(name = "openTime")
    public Timestamp getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Timestamp openTime) {
        this.openTime = openTime;
    }

    @Basic
    @Column(name = "closeTime")
    public Timestamp getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
    }

    @Basic
    @Column(name = "openLockTime")
    public Timestamp getOpenLockTime() {
        return openLockTime;
    }

    public void setOpenLockTime(Timestamp openLockTime) {
        this.openLockTime = openLockTime;
    }
}
