package com.wardrobe.platform.service.impl;

import com.wardrobe.common.po.OperatorInfo;
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
	
}
