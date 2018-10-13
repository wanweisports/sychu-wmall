package com.wardrobe.platform.service;

import com.wardrobe.common.po.SysDict;

import java.util.List;

public interface IDictService {

    List<SysDict> getDicts(String dictName);

    SysDict getDict(String dictName, String dictKey);

    String getDictValue(String dictName, String dictKey);

    String getDictValue(String dictName, int dictId);

    SysDict getDictById(int dictId);

    void addDict(SysDict sysDict);

    void deleteDict(int dictId);

    Number getDictIdByValue(String dictValue, String dictName);

}
