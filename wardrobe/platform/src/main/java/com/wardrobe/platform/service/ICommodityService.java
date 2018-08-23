package com.wardrobe.platform.service;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.po.CommodityColor;
import com.wardrobe.common.po.CommodityInfo;
import com.wardrobe.common.po.CommoditySize;
import com.wardrobe.common.po.CommodityStock;
import com.wardrobe.common.view.CommodityInputView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
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

    void addUpdateCommodityIn(CommodityInfo commodityInfo, MultipartHttpServletRequest request) throws IOException;

    void updateSizeIn(CommoditySize commoditySize);

    void deleteSizeIn(int sid);

    void updateCommodityStatusIn(int cid, String status);

    void updateCommodityHotIn(int cid, String hot);

    void updateCommodityNewlyIn(int cid, String newly);

    Map<String, Object> renderCommodityDetailIn(int cid);

    void saveStock(CommodityStock commodityStock);

    PageBean getStockListIn(CommodityInputView commodityInputView);

}
