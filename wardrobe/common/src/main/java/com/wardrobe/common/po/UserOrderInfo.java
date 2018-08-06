package com.wardrobe.common.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_order_info", schema = "")
public class UserOrderInfo {
    private int oid;
    private Integer uid;
    private Integer rid;
    private String orderType;
    private BigDecimal priceSum;
    private BigDecimal payPrice;
    private String payStatus;
    private String status;
    private Timestamp payTime;
    private String expressName;
    private String expressMobile;
    private String expressAreaId;
    private String expressAddress;
    private Timestamp sendTime;
    private BigDecimal freight;
    private Timestamp createTime;
    private Timestamp updateTime;
    private List<UserOrderDetail> userOrderDetails;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "oid")
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
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
    @Column(name = "rid")
    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "orderType")
    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
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
    @Column(name = "expressName")
    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    @Basic
    @Column(name = "expressMobile")
    public String getExpressMobile() {
        return expressMobile;
    }

    public void setExpressMobile(String expressMobile) {
        this.expressMobile = expressMobile;
    }

    @Basic
    @Column(name = "expressAreaId")
    public String getExpressAreaId() {
        return expressAreaId;
    }

    public void setExpressAreaId(String expressAreaId) {
        this.expressAreaId = expressAreaId;
    }

    @Basic
    @Column(name = "expressAddress")
    public String getExpressAddress() {
        return expressAddress;
    }

    public void setExpressAddress(String expressAddress) {
        this.expressAddress = expressAddress;
    }

    @Basic
    @Column(name = "sendTime")
    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    @Basic
    @Column(name = "freight")
    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
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
    public List<UserOrderDetail> getUserOrderDetails() {
        return userOrderDetails;
    }

    public void setUserOrderDetails(List<UserOrderDetail> userOrderDetails) {
        this.userOrderDetails = userOrderDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserOrderInfo that = (UserOrderInfo) o;

        if (oid != that.oid) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (rid != null ? !rid.equals(that.rid) : that.rid != null) return false;
        if (priceSum != null ? !priceSum.equals(that.priceSum) : that.priceSum != null) return false;
        if (payPrice != null ? !payPrice.equals(that.payPrice) : that.payPrice != null) return false;
        if (payStatus != null ? !payStatus.equals(that.payStatus) : that.payStatus != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (payTime != null ? !payTime.equals(that.payTime) : that.payTime != null) return false;
        if (expressName != null ? !expressName.equals(that.expressName) : that.expressName != null) return false;
        if (expressMobile != null ? !expressMobile.equals(that.expressMobile) : that.expressMobile != null)
            return false;
        if (expressAreaId != null ? !expressAreaId.equals(that.expressAreaId) : that.expressAreaId != null)
            return false;
        if (expressAddress != null ? !expressAddress.equals(that.expressAddress) : that.expressAddress != null)
            return false;
        if (sendTime != null ? !sendTime.equals(that.sendTime) : that.sendTime != null) return false;
        if (freight != null ? !freight.equals(that.freight) : that.freight != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = oid;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (rid != null ? rid.hashCode() : 0);
        result = 31 * result + (priceSum != null ? priceSum.hashCode() : 0);
        result = 31 * result + (payPrice != null ? payPrice.hashCode() : 0);
        result = 31 * result + (payStatus != null ? payStatus.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (payTime != null ? payTime.hashCode() : 0);
        result = 31 * result + (expressName != null ? expressName.hashCode() : 0);
        result = 31 * result + (expressMobile != null ? expressMobile.hashCode() : 0);
        result = 31 * result + (expressAreaId != null ? expressAreaId.hashCode() : 0);
        result = 31 * result + (expressAddress != null ? expressAddress.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + (freight != null ? freight.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
