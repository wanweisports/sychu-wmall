package com.wardrobe.platform.service;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.po.*;
import com.wardrobe.common.view.CommodityInputView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by cxs on 2018/7/30.
 */
public interface ICommodityService {

    PageBean getCommodityList(CommodityInputView commodityInputView);

    Map<String, Object> getCommodityDetail(int cid, int uid);

    Map<String, Object> getCommodityDetailSelected(int cid);

    String getFmImg(int cid);

    String getFmImg(int cid, boolean isOss);

    CommodityInfo getCommodityInfo(int cid);

    CommodityColor getCommodityColor(int coid);

    CommoditySize getCommoditySize(int sid);

    CommodityColor getCommodityColorByCid(int cid);

    List<CommoditySize> getCommoditySizeList(int cid);

    //后台管理
    PageBean getCommodityListIn(CommodityInputView commodityInputView);

    Map<String, Object> renderProductsAddIn(Integer cid);

    void addUpdateCommodityIn(CommodityInfo commodityInfo, String resourceIds, MultipartHttpServletRequest request) throws Exception;

    void addSizeIn(CommoditySize commoditySize);

    void deleteSizeIn(int sid);

    void updateCommodityStatusIn(int cid, String status);

    void updateCommodityHotIn(int cid, String hot);

    void updateCommodityNewlyIn(int cid, String newly);

    Map<String, Object> renderCommodityDetailIn(int cid);

    List<Map<String, Object>> getRecommendList(int cid);

    void deleteRecommend(int crid);

    void saveStock(CommodityStock commodityStock, boolean add);

    void saveOrderSubStock(UserOrderInfo userOrderInfo);

    void saveCommoditySaleCount(UserOrderInfo userOrderInfo);

    PageBean getStockListIn(CommodityInputView commodityInputView);

    void saveCommodityBanner(CommodityBanner commodityBanner, MultipartHttpServletRequest multipartRequest) throws IOException;

    void deleteCommodityBanner(int cbid);

    Map<String, Object> getCommodityBanners();

    List<Map<String, Object>> getCommodityBannersIn();

    void updateClickRate(int cid);

    PageBean getBannerCommodityListIn(CommodityInputView commodityInputView);

    void saveExcelCommoditys(MultipartHttpServletRequest multipartRequest) throws IOException;

    void deletecCommodity(int cid);

    void updateCommoditySeqNo(int cid, int seqNo);

    void saveRecommend(int cid, int recommendCid);

}
