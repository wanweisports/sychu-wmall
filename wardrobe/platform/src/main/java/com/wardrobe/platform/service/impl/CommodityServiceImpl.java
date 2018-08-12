package com.wardrobe.platform.service.impl;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.po.CommodityColor;
import com.wardrobe.common.po.CommodityInfo;
import com.wardrobe.common.po.CommoditySize;
import com.wardrobe.common.po.SysResources;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.CommodityInputView;
import com.wardrobe.platform.service.ICommodityService;
import com.wardrobe.platform.service.IDictService;
import com.wardrobe.platform.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by cxs on 2018/7/30.
 */
@Service
public class CommodityServiceImpl extends BaseService implements ICommodityService {

    @Autowired
    private IResourceService resourceService;

    @Override
    public PageBean getCommodityList(CommodityInputView commodityInputView){
        PageBean pageBean = getCommoditys(commodityInputView);
        List<Map<String, Object>> list = pageBean.getList();
        list.stream().forEach((commodity) ->{
            commodity.put("resource", resourceService.getResource(StrUtil.objToInt(commodity.get("cid")), IDBConstant.RESOURCE_COMMODITY_IMG, 0));//0表示封面图
        });
        return pageBean;
    }

    private PageBean getCommoditys(CommodityInputView commodityInputView){
        String category = commodityInputView.getCategory(); //regexp ',1,|,333,'
        String style = commodityInputView.getStyle();       //regexp ',1,|,333,'
        String material = commodityInputView.getMaterial(); //regexp ',1,|,333,'
        String newly = commodityInputView.getNewly();
        String hot = commodityInputView.getHot();

        StringBuilder headSql = new StringBuilder("SELECT ci.cid, ci.commName, ci.price");
        StringBuilder bodySql = new StringBuilder(" FROM commodity_info ci");
        StringBuilder whereSql = new StringBuilder(" WHERE 1=1");
        if(StrUtil.isNotBlank(category)){
            whereSql.append(" AND ci.category REGEXP :category");
        }
        if(StrUtil.isNotBlank(style)){
            whereSql.append(" AND ci.style REGEXP :style");
        }
        if(StrUtil.isNotBlank(material)){
            whereSql.append(" AND ci.material REGEXP :material");
        }
        if(IDBConstant.LOGIC_STATUS_YES.equals(newly)){
            whereSql.append(" AND ci.newly = :newly");
        }
        if(IDBConstant.LOGIC_STATUS_YES.equals(hot)){
            whereSql.append(" AND ci.hot = :hot");
        }
        whereSql.append(" ORDER BY ci.seqNo DESC, ci.createTime DESC");

        return super.getPageBean(headSql, bodySql, whereSql, commodityInputView);
    }

    @Override
    public PageBean getCommodityListIn(CommodityInputView commodityInputView){
        PageBean pageBean = getCommoditysIn(commodityInputView);
        List<Map<String, Object>> list = pageBean.getList();
        list.stream().forEach((map) ->{
            String category = StrUtil.objToStr(map.get("category"));
            String style = StrUtil.objToStr(map.get("style"));
            String material = StrUtil.objToStr(map.get("material"));
            map.put("categoryName", getTypes(category, IDBConstant.COMM_CATEGORY));
            map.put("styleName", getTypes(style, IDBConstant.COMM_STYLE));
            map.put("materialName", getTypes(material, IDBConstant.COMM_MATERIAL));
        });
        return pageBean;
    }

    private PageBean getCommoditysIn(CommodityInputView commodityInputView){
        String newly = commodityInputView.getNewly();
        String hot = commodityInputView.getHot();

        StringBuilder headSql = new StringBuilder("SELECT ci.*");
        StringBuilder bodySql = new StringBuilder(" FROM commodity_info ci");
        StringBuilder whereSql = new StringBuilder(" WHERE 1=1");
        if(IDBConstant.LOGIC_STATUS_YES.equals(newly)){
            whereSql.append(" AND ci.newly = :newly");
        }
        if(IDBConstant.LOGIC_STATUS_YES.equals(hot)){
            whereSql.append(" AND ci.hot = :hot");
        }
        whereSql.append(" ORDER BY ci.seqNo DESC, ci.createTime DESC");

        return super.getPageBean(headSql, bodySql, whereSql, commodityInputView);
    }

    @Override
    public void addCommodity(CommodityInfo commodityInfo){
        Timestamp timestamp = DateUtil.getNowDate();
        commodityInfo.setCreateTime(timestamp);
        baseDao.save(commodityInfo, null);

        ListIterator<CommodityColor> commodityColorListIterator = commodityInfo.getCommodityColors().listIterator();
        while (commodityColorListIterator.hasNext()){
            CommodityColor commodityColor = commodityColorListIterator.next();
            commodityColor.setCid(commodityInfo.getCid());
            commodityColor.setCreateTime(timestamp);
            baseDao.save(commodityColor, null);
            ListIterator<CommoditySize> commoditySizeListIterator = commodityColor.getCommoditySizes().listIterator();
            while (commodityColorListIterator.hasNext()){
                CommoditySize commoditySize = commoditySizeListIterator.next();
                commoditySize.setCid(commodityInfo.getCid());
                commoditySize.setCoid(commodityColor.getCoid());
                commoditySize.setCreateTime(timestamp);
                baseDao.save(commoditySize, null);
            }
        }
    }

}











