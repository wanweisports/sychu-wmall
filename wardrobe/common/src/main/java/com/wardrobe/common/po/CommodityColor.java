package com.wardrobe.common.po;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by 雷达 on 2018/7/30.
 */
@Entity
@Table(name = "commodity_color", schema = "")
public class CommodityColor {
    private int coid;
    private Integer cid;
    private String colorName;
    private Timestamp createTime;
    private Timestamp updateTime;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommodityColor that = (CommodityColor) o;

        if (coid != that.coid) return false;
        if (cid != null ? !cid.equals(that.cid) : that.cid != null) return false;
        if (colorName != null ? !colorName.equals(that.colorName) : that.colorName != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = coid;
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (colorName != null ? colorName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
