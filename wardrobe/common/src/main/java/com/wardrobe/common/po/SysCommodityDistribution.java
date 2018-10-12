package com.wardrobe.common.po;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by cxs on 2018/10/12.
 */
@Entity
@Table(name = "sys_commodity_distribution", schema = "")
public class SysCommodityDistribution {
    private int dbid;
    private Integer cid;
    private Integer sid;
    private String rfidEpc;
    private Integer dcid;
    private Date dbTime;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "dbid")
    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
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
    @Column(name = "rfidEpc")
    public String getRfidEpc() {
        return rfidEpc;
    }

    public void setRfidEpc(String rfidEpc) {
        this.rfidEpc = rfidEpc;
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
    @Column(name = "dbTime")
    public Date getDbTime() {
        return dbTime;
    }

    public void setDbTime(Date dbTime) {
        this.dbTime = dbTime;
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

        SysCommodityDistribution that = (SysCommodityDistribution) o;

        if (dbid != that.dbid) return false;
        if (cid != null ? !cid.equals(that.cid) : that.cid != null) return false;
        if (sid != null ? !sid.equals(that.sid) : that.sid != null) return false;
        if (rfidEpc != null ? !rfidEpc.equals(that.rfidEpc) : that.rfidEpc != null) return false;
        if (dcid != null ? !dcid.equals(that.dcid) : that.dcid != null) return false;
        if (dbTime != null ? !dbTime.equals(that.dbTime) : that.dbTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dbid;
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (sid != null ? sid.hashCode() : 0);
        result = 31 * result + (rfidEpc != null ? rfidEpc.hashCode() : 0);
        result = 31 * result + (dcid != null ? dcid.hashCode() : 0);
        result = 31 * result + (dbTime != null ? dbTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
