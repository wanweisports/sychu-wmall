package com.wardrobe.common.po;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by cxs on 2018/9/19.
 */
@Entity
@Table(name = "commodity_banner", schema = "")
public class CommodityBanner {
    private int cbid;
    private Integer cid;
    private String title;
    private String url;
    private Integer seqNo;
    private Timestamp createTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cbid")
    public int getCbid() {
        return cbid;
    }

    public void setCbid(int cbid) {
        this.cbid = cbid;
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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

        CommodityBanner that = (CommodityBanner) o;

        if (cbid != that.cbid) return false;
        if (cid != null ? !cid.equals(that.cid) : that.cid != null) return false;
        if (seqNo != null ? !seqNo.equals(that.seqNo) : that.seqNo != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cbid;
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (seqNo != null ? seqNo.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
