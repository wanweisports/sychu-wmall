package com.wardrobe.common.po;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by cxs on 2018/9/17.
 */
@Entity
@Table(name = "user_collection", schema = "")
public class UserCollection {
    private int cnid;
    private Integer uid;
    private Integer cid;
    private Timestamp createTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cnid")
    public int getCnid() {
        return cnid;
    }

    public void setCnid(int cnid) {
        this.cnid = cnid;
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
    @Column(name = "cid")
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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

        UserCollection that = (UserCollection) o;

        if (cnid != that.cnid) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (cid != null ? !cid.equals(that.cid) : that.cid != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cnid;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
