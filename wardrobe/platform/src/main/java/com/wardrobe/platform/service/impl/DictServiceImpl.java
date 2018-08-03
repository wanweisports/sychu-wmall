package com.wardrobe.platform.service.impl;

import com.wardrobe.common.po.SysDict;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.service.IDictService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl extends BaseService implements IDictService {

    @Override
    public List<SysDict> getDicts(String dictName){
        return baseDao.queryByHql("FROM SysDict d WHERE dictName = ?1 ORDER BY seqNo", dictName);
    }

    @Override
    public SysDict getDict(String dictName, String dictKey){
        return baseDao.queryByHqlFirst("FROM SysDict d WHERE dictName = ?1 AND dictKey = ?2", dictName, dictKey);
    }

    @Override
    public String getDictValue(String dictName, String dictKey){
        return StrUtil.objToStr(baseDao.getUniqueObjectResult("SELECT dictValue FROM sys_dict WHERE  dictName = ?1 AND dictKey = ?2", dictName, dictKey));
    }

}
