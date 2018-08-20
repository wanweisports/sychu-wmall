package com.wardrobe.platform.service;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.po.CommodityColor;
import com.wardrobe.common.po.CommodityInfo;
import com.wardrobe.common.view.CommodityInputView;

import java.util.Map;

/**
 * Created by cxs on 2018/7/30.
 */
public interface ICommodityService {

    PageBean getCommodityList(CommodityInputView commodityInputView);

    Map<String, Object> getCommodityDetail(int cid);

    //后台管理
    PageBean getCommodityListIn(CommodityInputView commodityInputView);

    Map<String, Object> renderProductsAddIn(Integer cid);

    void addCommodityIn(CommodityInfo commodityInfo, CommodityColor commodityColor);

}
