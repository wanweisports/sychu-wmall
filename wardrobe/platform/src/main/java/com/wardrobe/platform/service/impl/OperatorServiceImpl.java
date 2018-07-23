package com.wardrobe.platform.service.impl;

import com.wardrobe.platform.service.IOperatorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OperatorServiceImpl extends BaseService implements IOperatorService {

    @Override
    public List<Map<String, Object>> get(){
        return baseDao.queryBySql("SELECT * FROM sys_user");
    }
	
}
