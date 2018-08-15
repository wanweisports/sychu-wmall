package com.wardrobe.common.po;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by cxs on 2018/8/15.
 */
@Entity
@Table(name = "sys_rank_info", schema = "")
public class SysRankInfo {
    private int rid;
    private int rank;
    private String rankName;
    private Integer rankScore;
    private BigDecimal rankDiscount;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "rid")
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "rank")
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "rankName")
    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    @Basic
    @Column(name = "rankScore")
    public Integer getRankScore() {
        return rankScore;
    }

    public void setRankScore(Integer rankScore) {
        this.rankScore = rankScore;
    }

    @Basic
    @Column(name = "rankDiscount")
    public BigDecimal getRankDiscount() {
        return rankDiscount;
    }

    public void setRankDiscount(BigDecimal rankDiscount) {
        this.rankDiscount = rankDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRankInfo that = (SysRankInfo) o;

        if (rid != that.rid) return false;
        if (rankName != null ? !rankName.equals(that.rankName) : that.rankName != null) return false;
        if (rankScore != null ? !rankScore.equals(that.rankScore) : that.rankScore != null) return false;
        if (rankDiscount != null ? !rankDiscount.equals(that.rankDiscount) : that.rankDiscount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rid;
        result = 31 * result + (rankName != null ? rankName.hashCode() : 0);
        result = 31 * result + (rankScore != null ? rankScore.hashCode() : 0);
        result = 31 * result + (rankDiscount != null ? rankDiscount.hashCode() : 0);
        return result;
    }
}
