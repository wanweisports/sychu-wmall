package com.wardrobe.platform.service;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.view.CommodityInputView;

/**
 * Created by cxs on 2018/7/30.
 */
public interface ICommodityService {

    PageBean getCommodityList(CommodityInputView commodityInputView);

    PageBean getCommoditys(CommodityInputView commodityInputView);

}
