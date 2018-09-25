package com.wardrobe.common.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by cxs on 2018/9/25.
 */
@Entity
@Table(name = "sys_coupon_rule", schema = "")
public class SysCouponRule {
    private int crid;
    private BigDecimal couponPrice;
    private BigDecimal fullPrice;
    private Integer dueNum;
    private String rule;
    private Integer serviceType;
    private Timestamp crType;
    private Timestamp crTime;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "crid")
    public int getCrid() {
        return crid;
    }

    public void setCrid(int crid) {
        this.crid = crid;
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
    @Column(name = "fullPrice")
    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    @Basic
    @Column(name = "dueNum")
    public Integer getDueNum() {
        return dueNum;
    }

    public void setDueNum(Integer dueNum) {
        this.dueNum = dueNum;
    }

    @Basic
    @Column(name = "rule")
    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Basic
    @Column(name = "serviceType")
    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Timestamp getCrType() {
        return crType;
    }

    public void setCrType(Timestamp crType) {
        this.crType = crType;
    }

    public Timestamp getCrTime() {
        return crTime;
    }

    public void setCrTime(Timestamp crTime) {
        this.crTime = crTime;
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

        SysCouponRule that = (SysCouponRule) o;

        if (crid != that.crid) return false;
        if (couponPrice != null ? !couponPrice.equals(that.couponPrice) : that.couponPrice != null) return false;
        if (fullPrice != null ? !fullPrice.equals(that.fullPrice) : that.fullPrice != null) return false;
        if (dueNum != null ? !dueNum.equals(that.dueNum) : that.dueNum != null) return false;
        if (rule != null ? !rule.equals(that.rule) : that.rule != null) return false;
        if (serviceType != null ? !serviceType.equals(that.serviceType) : that.serviceType != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = crid;
        result = 31 * result + (couponPrice != null ? couponPrice.hashCode() : 0);
        result = 31 * result + (fullPrice != null ? fullPrice.hashCode() : 0);
        result = 31 * result + (dueNum != null ? dueNum.hashCode() : 0);
        result = 31 * result + (rule != null ? rule.hashCode() : 0);
        result = 31 * result + (serviceType != null ? serviceType.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
