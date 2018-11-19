package com.wardrobe.platform.service;

import com.wardrobe.common.po.OperatorInfo;

import java.util.List;
import java.util.Map;

public interface IOperatorService {

    OperatorInfo loginIn(OperatorInfo operatorInfo);

    String getOperatorInfoName(Integer operatorId);

    OperatorInfo getOperatorInfo(int operatorId);

}
