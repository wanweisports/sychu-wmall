package com.wardrobe.common.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "reserve_order_detail", schema = "")
public class ReserveOrderDetail {
    private int rdid;
    private Integer roid;
    private Integer cid;
    private String resItemName;
    private String resItemColor;
    private String resItemImg;
    private String resItemSize;
    private BigDecimal resItemPrice;
    private Integer resItemCount;
    private BigDecimal resItemPriceSum;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "rdid")
    public int getRdid() {
        return rdid;
    }

    public void setRdid(int rdid) {
        this.rdid = rdid;
    }

    @Basic
    @Column(name = "roid")
    public Integer getRoid() {
        return roid;
    }

    public void setRoid(Integer roid) {
        this.roid = roid;
    }

    @Basic
    @Column(name = "cid")
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "resItemName")
    public String getResItemName() {
        return resItemName;
    }

    public void setResItemName(String resItemName) {
        this.resItemName = resItemName;
    }

    @Basic
    @Column(name = "resItemColor")
    public String getResItemColor() {
        return resItemColor;
    }

    public void setResItemColor(String resItemColor) {
        this.resItemColor = resItemColor;
    }

    @Basic
    @Column(name = "resItemImg")
    public String getResItemImg() {
        return resItemImg;
    }

    public void setResItemImg(String resItemImg) {
        this.resItemImg = resItemImg;
    }

    @Basic
    @Column(name = "resItemSize")
    public String getResItemSize() {
        return resItemSize;
    }

    public void setResItemSize(String resItemSize) {
        this.resItemSize = resItemSize;
    }

    @Basic
    @Column(name = "resItemPrice")
    public BigDecimal getResItemPrice() {
        return resItemPrice;
    }

    public void setResItemPrice(BigDecimal resItemPrice) {
        this.resItemPrice = resItemPrice;
    }

    @Basic
    @Column(name = "resItemCount")
    public Integer getResItemCount() {
        return resItemCount;
    }

    public void setResItemCount(Integer resItemCount) {
        this.resItemCount = resItemCount;
    }

    @Basic
    @Column(name = "resItemPriceSum")
    public BigDecimal getResItemPriceSum() {
        return resItemPriceSum;
    }

    public void setResItemPriceSum(BigDecimal resItemPriceSum) {
        this.resItemPriceSum = resItemPriceSum;
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

        ReserveOrderDetail that = (ReserveOrderDetail) o;

        if (rdid != that.rdid) return false;
        if (roid != null ? !roid.equals(that.roid) : that.roid != null) return false;
        if (cid != null ? !cid.equals(that.cid) : that.cid != null) return false;
        if (resItemName != null ? !resItemName.equals(that.resItemName) : that.resItemName != null) return false;
        if (resItemColor != null ? !resItemColor.equals(that.resItemColor) : that.resItemColor != null) return false;
        if (resItemImg != null ? !resItemImg.equals(that.resItemImg) : that.resItemImg != null) return false;
        if (resItemSize != null ? !resItemSize.equals(that.resItemSize) : that.resItemSize != null) return false;
        if (resItemPrice != null ? !resItemPrice.equals(that.resItemPrice) : that.resItemPrice != null) return false;
        if (resItemCount != null ? !resItemCount.equals(that.resItemCount) : that.resItemCount != null) return false;
        if (resItemPriceSum != null ? !resItemPriceSum.equals(that.resItemPriceSum) : that.resItemPriceSum != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rdid;
        result = 31 * result + (roid != null ? roid.hashCode() : 0);
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (resItemName != null ? resItemName.hashCode() : 0);
        result = 31 * result + (resItemColor != null ? resItemColor.hashCode() : 0);
        result = 31 * result + (resItemImg != null ? resItemImg.hashCode() : 0);
        result = 31 * result + (resItemSize != null ? resItemSize.hashCode() : 0);
        result = 31 * result + (resItemPrice != null ? resItemPrice.hashCode() : 0);
        result = 31 * result + (resItemCount != null ? resItemCount.hashCode() : 0);
        result = 31 * result + (resItemPriceSum != null ? resItemPriceSum.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
