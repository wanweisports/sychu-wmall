package com.wardrobe.platform.service.impl;

import com.wardrobe.platform.service.ISysRankService;
import org.springframework.stereotype.Service;

/**
 * Created by cxs on 2018/8/15.
 */
@Service
public class SysRankServiceImpl extends BaseService implements ISysRankService {

    @Override
    public Integer getRank(int scopeSum, int oldRank){
        Number rank = baseDao.getUniqueResult("SELECT rank FROM sys_rank_info WHERE rankScore >= ? ORDER BY rid DESC LIMIT 1", scopeSum);
        if(rank != null){
            int r = rank.intValue();
            if(r > oldRank) return r;
        }
        return null;
    }

}
