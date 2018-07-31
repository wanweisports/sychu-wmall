package com.wardrobe.platform.service.impl;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.po.SysResources;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.CommodityInputView;
import com.wardrobe.platform.service.ICommodityService;
import com.wardrobe.platform.service.IDictService;
import com.wardrobe.platform.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private IDictService dictService;

    @Override
    public PageBean getCommodityList(CommodityInputView commodityInputView){
        PageBean pageBean = getCommoditys(commodityInputView);
        ListIterator<Map<String, Object>> listIterator = pageBean.getList().listIterator();
        while (listIterator.hasNext()){
            Map<String, Object> commodity = listIterator.next();
            commodity.put("resource", resourceService.getResource(StrUtil.objToInt(commodity.get("cid")), IDBConstant.RESOURCE_COMMODITY_IMG, 0));//0表示封面图
        }
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
        ListIterator<Map<String, Object>> listIterator = pageBean.getList().listIterator();
        while (listIterator.hasNext()){
            Map<String, Object> map = listIterator.next();
            String category = StrUtil.objToStr(map.get("category"));
            String style = StrUtil.objToStr(map.get("style"));
            String material = StrUtil.objToStr(map.get("material"));
            map.put("categoryName", getCommodityTypes(category, IDBConstant.COMM_CATEGORY));
            map.put("styleName", getCommodityTypes(style, IDBConstant.COMM_STYLE));
            map.put("materialName", getCommodityTypes(material, IDBConstant.COMM_MATERIAL));
        }
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

    private String getCommodityTypes(String types, String dictName){
        StringBuilder typesSd = new StringBuilder();
        String[] typeArr = types.split(",");
        for(String type : typeArr){
            if(StrUtil.isNotBlank(type)){
                if(typesSd.length() > 0) typesSd.append("、");
                typesSd.append(dictService.getDictValue(dictName, type));
            }
        }
        return typesSd.toString();
    }

    public void addCommodity(){
        
    }

}
