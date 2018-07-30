package com.wardrobe.platform.service;

import com.wardrobe.common.po.SysDict;

import java.util.List;

public interface IDictService {

    List<SysDict> getDicts(String dictName);

}
