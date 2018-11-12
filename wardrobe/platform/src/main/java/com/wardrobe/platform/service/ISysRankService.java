package com.wardrobe.platform.service;

import com.wardrobe.common.po.SysRankInfo;

/**
 * Created by cxs on 2018/8/15.
 */
public interface ISysRankService {

    Integer getRank(int scopeSum, int oldRank);

    SysRankInfo getRankInfoByRank(int rank);

    int getNextScore(int rank);

    int getMaxRank();

}
