package com.wardrobe.platform.service.impl;

import com.wardrobe.common.po.SysDict;
import com.wardrobe.common.util.DateUtil;
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

    @Override
    public String getDictValue(String dictName, int dictId){
        return StrUtil.objToStr(baseDao.getUniqueObjectResult("SELECT dictValue FROM sys_dict WHERE  dictName = ?1 AND dictId = ?2", dictName, dictId));
    }

    @Override
    public SysDict getDictById(int dictId){
        return baseDao.getToEvict(SysDict.class, dictId);
    }

    @Override
    public void addDict(SysDict sysDict){
        sysDict.setCreateTime(DateUtil.getNowDate());
        baseDao.save(sysDict, null);
    }

    @Override
    public void deleteDict(int dictId){
        baseDao.delete(getDictById(dictId));
    }

}
