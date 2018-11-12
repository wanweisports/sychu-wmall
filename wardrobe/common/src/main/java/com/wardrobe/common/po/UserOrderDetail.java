package com.wardrobe.common.po;

import com.wardrobe.common.constant.IDBConstant;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_order_detail", schema = "")
public class UserOrderDetail {
    private int odid;
    private Integer oid;
    private Integer cid;
    private Integer sid;
    private String itemName;
    private String itemColor;
    private String itemSize;
    private String itemImg;
    private BigDecimal itemPrice;
    private Integer itemCount;
    private BigDecimal itemPriceSum;
    private String itemType = IDBConstant.LOGIC_STATUS_YES; //1：普通 2：赠送
    private Integer dbid; //配送表id
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "odid")
    public int getOdid() {
        return odid;
    }

    public void setOdid(int odid) {
        this.odid = odid;
    }

    @Basic
    @Column(name = "oid")
    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
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
    @Column(name = "sid")
    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "itemName")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "itemColor")
    public String getItemColor() {
        return itemColor;
    }

    public void setItemColor(String itemColor) {
        this.itemColor = itemColor;
    }

    @Basic
    @Column(name = "itemSize")
    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    @Basic
    @Column(name = "itemImg")
    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    @Basic
    @Column(name = "itemPrice")
    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Basic
    @Column(name = "itemCount")
    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    @Basic
    @Column(name = "itemPriceSum")
    public BigDecimal getItemPriceSum() {
        return itemPriceSum;
    }

    public void setItemPriceSum(BigDecimal itemPriceSum) {
        this.itemPriceSum = itemPriceSum;
    }

    @Basic
    @Column(name = "itemType")
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @Basic
    @Column(name = "dbid")
    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
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

        UserOrderDetail that = (UserOrderDetail) o;

        if (odid != that.odid) return false;
        if (oid != null ? !oid.equals(that.oid) : that.oid != null) return false;
        if (cid != null ? !cid.equals(that.cid) : that.cid != null) return false;
        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;
        if (itemColor != null ? !itemColor.equals(that.itemColor) : that.itemColor != null) return false;
        if (itemSize != null ? !itemSize.equals(that.itemSize) : that.itemSize != null) return false;
        if (itemImg != null ? !itemImg.equals(that.itemImg) : that.itemImg != null) return false;
        if (itemPrice != null ? !itemPrice.equals(that.itemPrice) : that.itemPrice != null) return false;
        if (itemCount != null ? !itemCount.equals(that.itemCount) : that.itemCount != null) return false;
        if (itemPriceSum != null ? !itemPriceSum.equals(that.itemPriceSum) : that.itemPriceSum != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = odid;
        result = 31 * result + (oid != null ? oid.hashCode() : 0);
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (itemColor != null ? itemColor.hashCode() : 0);
        result = 31 * result + (itemSize != null ? itemSize.hashCode() : 0);
        result = 31 * result + (itemImg != null ? itemImg.hashCode() : 0);
        result = 31 * result + (itemPrice != null ? itemPrice.hashCode() : 0);
        result = 31 * result + (itemCount != null ? itemCount.hashCode() : 0);
        result = 31 * result + (itemPriceSum != null ? itemPriceSum.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
