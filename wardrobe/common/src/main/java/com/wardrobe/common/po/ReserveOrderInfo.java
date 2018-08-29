package com.wardrobe.common.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "reserve_order_info", schema = "")
public class ReserveOrderInfo {
    private int roid;
    private Integer uid;
    private Integer dcid;
    private BigDecimal priceSum;
    private BigDecimal payPrice;
    private String payStatus;
    private String status;
    private Timestamp payTime;
    private Timestamp reserveStartTime;
    private Timestamp reserveEndTime;
    private Timestamp createTime;
    private Timestamp updateTime;

    private Integer did;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "roid")
    public int getRoid() {
        return roid;
    }

    public void setRoid(int roid) {
        this.roid = roid;
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
    @Column(name = "dcid")
    public Integer getDcid() {
        return dcid;
    }

    public void setDcid(Integer dcid) {
        this.dcid = dcid;
    }

    @Basic
    @Column(name = "priceSum")
    public BigDecimal getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(BigDecimal priceSum) {
        this.priceSum = priceSum;
    }

    @Basic
    @Column(name = "payPrice")
    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    @Basic
    @Column(name = "payStatus")
    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
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
    @Column(name = "payTime")
    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    @Basic
    @Column(name = "reserveStartTime")
    public Timestamp getReserveStartTime() {
        return reserveStartTime;
    }

    public void setReserveStartTime(Timestamp reserveStartTime) {
        this.reserveStartTime = reserveStartTime;
    }

    @Basic
    @Column(name = "reserveEndTime")
    public Timestamp getReserveEndTime() {
        return reserveEndTime;
    }

    public void setReserveEndTime(Timestamp reserveEndTime) {
        this.reserveEndTime = reserveEndTime;
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

    @Transient
    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReserveOrderInfo that = (ReserveOrderInfo) o;

        if (roid != that.roid) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (dcid != null ? !dcid.equals(that.dcid) : that.dcid != null) return false;
        if (priceSum != null ? !priceSum.equals(that.priceSum) : that.priceSum != null) return false;
        if (payPrice != null ? !payPrice.equals(that.payPrice) : that.payPrice != null) return false;
        if (payStatus != null ? !payStatus.equals(that.payStatus) : that.payStatus != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (payTime != null ? !payTime.equals(that.payTime) : that.payTime != null) return false;
        if (reserveStartTime != null ? !reserveStartTime.equals(that.reserveStartTime) : that.reserveStartTime != null)
            return false;
        if (reserveEndTime != null ? !reserveEndTime.equals(that.reserveEndTime) : that.reserveEndTime != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roid;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (dcid != null ? dcid.hashCode() : 0);
        result = 31 * result + (priceSum != null ? priceSum.hashCode() : 0);
        result = 31 * result + (payPrice != null ? payPrice.hashCode() : 0);
        result = 31 * result + (payStatus != null ? payStatus.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (payTime != null ? payTime.hashCode() : 0);
        result = 31 * result + (reserveStartTime != null ? reserveStartTime.hashCode() : 0);
        result = 31 * result + (reserveEndTime != null ? reserveEndTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
