package com.wardrobe.common.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "commodity_color", schema = "")
public class CommodityColor {
    private int coid;
    private Integer cid;
    private String colorName;
    private Timestamp createTime;
    private Timestamp updateTime;

    private List<CommoditySize> commoditySizes;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "coid")
    public int getCoid() {
        return coid;
    }

    public void setCoid(int coid) {
        this.coid = coid;
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
    @Column(name = "colorName")
    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
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
    public List<CommoditySize> getCommoditySizes() {
        return commoditySizes;
    }

    public void setCommoditySizes(List<CommoditySize> commoditySizes) {
        this.commoditySizes = commoditySizes;
    }

}
