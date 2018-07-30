package com.wardrobe.platform.service.impl;

import com.wardrobe.common.po.SysDict;
import com.wardrobe.platform.service.IDictService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl extends BaseService implements IDictService {

    @Override
    public List<SysDict> getDicts(String dictName){
        return baseDao.queryByHql("FROM SysDict d WHERE dictName = ?", dictName);
    }

}
