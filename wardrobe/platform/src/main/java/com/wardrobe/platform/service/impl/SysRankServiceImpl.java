package com.wardrobe.platform.service.impl;

import com.wardrobe.common.po.SysRankInfo;
import com.wardrobe.platform.service.ISysRankService;
import org.springframework.stereotype.Service;

/**
 * Created by cxs on 2018/8/15.
 */
@Service
public class SysRankServiceImpl extends BaseService implements ISysRankService {

    @Override
    public Integer getRank(int scopeSum, int oldRank){
        Number rank = baseDao.getUniqueResult("SELECT rank FROM sys_rank_info WHERE rankScore <= ?1 ORDER BY rid DESC LIMIT 1", scopeSum);
        if(rank != null){
            int r = rank.intValue();
            if(r > oldRank) return r;
        }
        return null;
    }

    @Override
    public SysRankInfo getRankInfoByRank(int rank){
        return baseDao.queryByHqlFirst("FROM SysRankInfo WHERE rank = ?1", rank);
    }

    @Override
    public int getNextScore(int rank){
        if(getMaxRank() == rank) return 0;
        return baseDao.getUniqueResult("SELECT rankScore FROM sys_rank_info WHERE rank > ?1 ORDER BY rid LIMIT 1", rank).intValue();
    }

    @Override
    public int getMaxRank(){
        return baseDao.getUniqueResult("SELECT rank FROM sys_rank_info ORDER BY rid DESC LIMIT 1").intValue();
    }

}
