package com.wardrobe.common.po;

import com.wardrobe.common.constant.IDBConstant;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "commodity_info", schema = "")
public class CommodityInfo {
    private int cid;
    private String commName;
    private String category;
    private String style;
    private String material;
    private String productDesc;
    private BigDecimal price;
    private BigDecimal couPrice;
    private Integer saleCount = 0;
    private String status = IDBConstant.LOGIC_STATUS_YES;
    private Integer seqNo = 0;
    private String hot = IDBConstant.LOGIC_STATUS_NO;
    private String newly = IDBConstant.LOGIC_STATUS_NO;
    private Integer groupId;
    private String commNo;
    private String brandName;
    private Integer clickRate = 0;
    private Timestamp createTime;
    private Timestamp updateTime;

    private CommodityColor commodityColor;
    private String color;
    private Integer cidMapping;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cid")
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "commName")
    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "style")
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Basic
    @Column(name = "material")
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Basic
    @Column(name = "productDesc")
    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "couPrice")
    public BigDecimal getCouPrice() {
        return couPrice;
    }

    public void setCouPrice(BigDecimal couPrice) {
        this.couPrice = couPrice;
    }

    @Basic
    @Column(name = "saleCount")
    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
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
    @Column(name = "seqNo")
    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    @Basic
    @Column(name = "hot")
    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    @Basic
    @Column(name = "newly")
    public String getNewly() {
        return newly;
    }

    public void setNewly(String newly) {
        this.newly = newly;
    }

    @Basic
    @Column(name = "groupId")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "commNo")
    public String getCommNo() {
        return commNo;
    }

    public void setCommNo(String commNo) {
        this.commNo = commNo;
    }

    @Basic
    @Column(name = "brandName")
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }


    @Basic
    @Column(name = "clickRate")
    public Integer getClickRate() {
        return clickRate;
    }

    public void setClickRate(Integer clickRate) {
        this.clickRate = clickRate;
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
    public CommodityColor getCommodityColor() {
        return commodityColor;
    }

    public void setCommodityColor(CommodityColor commodityColor) {
        this.commodityColor = commodityColor;
    }

    @Transient
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Transient
    public Integer getCidMapping() {
        return cidMapping;
    }

    public void setCidMapping(Integer cidMapping) {
        this.cidMapping = cidMapping;
    }
}
