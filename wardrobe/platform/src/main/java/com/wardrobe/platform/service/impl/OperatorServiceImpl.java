package com.wardrobe.platform.service.impl;

import com.wardrobe.common.po.OperatorInfo;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.service.IOperatorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OperatorServiceImpl extends BaseService implements IOperatorService {

    @Override
    public OperatorInfo loginIn(OperatorInfo operatorInfo){
        return baseDao.queryByHqlFirst("FROM OperatorInfo WHERE operatorAccount = ?1 AND operatorPwd = ?2", operatorInfo.getOperatorAccount(), operatorInfo.getOperatorPwd());
    }

    @Override
    public String getOperatorInfoName(Integer operatorId){
        if(operatorId == null) return StrUtil.EMPTY;
        return StrUtil.objToStrDefEmpty(baseDao.getUniqueObjectResult("SELECT operatorName FROM operator_info WHERE operatorId = ?1", operatorId));
    }

    @Override
    public OperatorInfo getOperatorInfo(int operatorId){
        return baseDao.getToEvict(OperatorInfo.class, operatorId);
    }
	
}
