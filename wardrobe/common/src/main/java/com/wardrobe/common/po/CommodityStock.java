package com.wardrobe.common.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by cxs on 2018/8/23.
 */
@Entity
@Table(name = "commodity_stock", schema = "")
public class CommodityStock {
    private int skid;
    private int sid;
    private String type;
    private int num;
    private String remark;
    private Integer operatorId;
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());

    public CommodityStock() {
    }

    public CommodityStock(int sid, String type, int num, String remark, Integer operatorId) {
        this.sid = sid;
        this.type = type;
        this.num = num;
        this.remark = remark;
        this.operatorId = operatorId;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "skid")
    public int getSkid() {
        return skid;
    }

    public void setSkid(int skid) {
        this.skid = skid;
    }

    @Basic
    @Column(name = "sid")
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "num")
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "operatorId")
    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
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

        CommodityStock that = (CommodityStock) o;

        if (skid != that.skid) return false;
        if (sid != that.sid) return false;
        if (num != that.num) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (operatorId != null ? !operatorId.equals(that.operatorId) : that.operatorId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = skid;
        result = 31 * result + sid;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + num;
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (operatorId != null ? operatorId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
