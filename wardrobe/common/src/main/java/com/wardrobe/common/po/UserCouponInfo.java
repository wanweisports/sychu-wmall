package com.wardrobe.common.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by 雷达 on 2018/7/30.
 */
@Entity
@Table(name = "user_coupon_info", schema = "")
public class UserCouponInfo {
    private int cpid;
    private Integer uid;
    private String serviceType;
    private Integer serviceId;
    private BigDecimal couponPrice;
    private String status;
    private Timestamp dueTime;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cpid")
    public int getCpid() {
        return cpid;
    }

    public void setCpid(int cpid) {
        this.cpid = cpid;
    }

    @Basic
    @Column(name = "uid")
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "serviceType")
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @Basic
    @Column(name = "serviceId")
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Basic
    @Column(name = "couponPrice")
    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
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
    @Column(name = "dueTime")
    public Timestamp getDueTime() {
        return dueTime;
    }

    public void setDueTime(Timestamp dueTime) {
        this.dueTime = dueTime;
    }

    @Basic
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "updateTime")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCouponInfo that = (UserCouponInfo) o;

        if (cpid != that.cpid) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (serviceType != null ? !serviceType.equals(that.serviceType) : that.serviceType != null) return false;
        if (serviceId != null ? !serviceId.equals(that.serviceId) : that.serviceId != null) return false;
        if (couponPrice != null ? !couponPrice.equals(that.couponPrice) : that.couponPrice != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (dueTime != null ? !dueTime.equals(that.dueTime) : that.dueTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cpid;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (serviceType != null ? serviceType.hashCode() : 0);
        result = 31 * result + (serviceId != null ? serviceId.hashCode() : 0);
        result = 31 * result + (couponPrice != null ? couponPrice.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (dueTime != null ? dueTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
