package com.wardrobe.platform.service.impl;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.po.CommodityColor;
import com.wardrobe.common.po.CommodityInfo;
import com.wardrobe.common.po.CommoditySize;
import com.wardrobe.common.po.SysResources;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.FileUtil;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.CommodityInputView;
import com.wardrobe.platform.service.ICommodityService;
import com.wardrobe.platform.service.IDictService;
import com.wardrobe.platform.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

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
            SysResources sysResources = resourceService.getResourceByParentId(StrUtil.objToInt(commodity.get("cid")), IDBConstant.RESOURCE_COMMODITY_IMG, 0);
            commodity.put("resourcePath", sysResources != null ? sysResources.getResourcePath() : StrUtil.EMPTY);//0表示封面图
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
    public Map<String, Object> getCommodityDetail(int cid){
        Map<String, Object> data = new HashMap<>(6, 1);
        CommodityInfo commodityInfo = getCommodityInfo(cid);
        data.put("sizes", getCommoditySizes(cid));
        data.put("colors", getCommodityColors(commodityInfo.getGroupId()));
        data.put("commName", commodityInfo.getCommName());
        data.put("price", commodityInfo.getPrice());
        data.put("desc", commodityInfo.getProductDesc());
        data.put("resources", resourceService.getResourcesPath(resourceService.getResourcesByParentId(cid, IDBConstant.RESOURCE_COMMODITY_IMG)));
        return data;
    }

    private CommodityInfo getCommodityInfo(int cid){
        return baseDao.getToEvict(CommodityInfo.class, cid);
    }

    private CommodityColor getCommodityColor(int coid){
        return baseDao.getToEvict(CommodityColor.class, coid);
    }

    private CommoditySize getCommoditySize(int sid){
        return baseDao.getToEvict(CommoditySize.class, sid);
    }

    private CommodityColor getCommodityColorByCid(int cid){
        return baseDao.queryByHqlFirst("FROM CommodityColor WHERE cid = ?1", cid);
    }

    private List<Map<String, Object>> getCommodityColors(int groupId){
        return baseDao.queryBySql("SELECT cc.cid, cc.colorName FROM commodity_info ci, commodity_color cc WHERE ci.cid = cc.cid AND ci.groupId = ?1", groupId);
    }

    private String[] getCommoditySizes(int cid){
        String sizes = StrUtil.objToStr(baseDao.getUniqueObjectResult("SELECT GROUP_CONCAT(size) FROM commodity_size WHERE cid = ?1", cid));
        return sizes.split(",");
    }

    private List<CommoditySize> getCommoditySizeList(int cid){
        return baseDao.queryByHql("FROM CommoditySize WHERE cid = ?1", cid);
    }

    @Override
    public PageBean getCommodityListIn(CommodityInputView commodityInputView){
        PageBean pageBean = getCommoditysIn(commodityInputView);
        List<Map<String, Object>> list = pageBean.getList();
        list.stream().forEach((commodity) -> {
            String category = StrUtil.objToStr(commodity.get("category"));
            String style = StrUtil.objToStr(commodity.get("style"));
            String material = StrUtil.objToStr(commodity.get("material"));
            commodity.put("categoryName", getTypes(category, IDBConstant.COMM_CATEGORY));
            commodity.put("styleName", getTypes(style, IDBConstant.COMM_STYLE));
            commodity.put("materialName", getTypes(material, IDBConstant.COMM_MATERIAL));
            commodity.put("statusName", dictService.getDict(IDBConstant.COMM_STATUS, StrUtil.objToStr(commodity.get("status"))).getDictValue());
            SysResources sysResources = resourceService.getResourceByParentId(StrUtil.objToInt(commodity.get("cid")), IDBConstant.RESOURCE_COMMODITY_IMG, 0);
            commodity.put("resourcePath", sysResources != null ? sysResources.getResourcePath() : StrUtil.EMPTY);//0表示封面图
        });
        return pageBean;
    }

    private PageBean getCommoditysIn(CommodityInputView commodityInputView){
        String newly = commodityInputView.getNewly();
        String hot = commodityInputView.getHot();
        String commName = commodityInputView.getCommName();
        String status = commodityInputView.getStatus();

        StringBuilder headSql = new StringBuilder("SELECT ci.*");
        StringBuilder bodySql = new StringBuilder(" FROM commodity_info ci");
        StringBuilder whereSql = new StringBuilder(" WHERE 1=1");
        if(IDBConstant.LOGIC_STATUS_YES.equals(newly)){
            whereSql.append(" AND ci.newly = :newly");
        }
        if(IDBConstant.LOGIC_STATUS_YES.equals(hot)){
            whereSql.append(" AND ci.hot = :hot");
        }
        if(StrUtil.isNotBlank(commName)){
            whereSql.append(" AND ci.commName = :commName");
        }
        if(StrUtil.isNotBlank(status)){
            whereSql.append(" AND ci.status = :status");
        }
        whereSql.append(" ORDER BY ci.seqNo DESC, ci.createTime DESC");

        return super.getPageBean(headSql, bodySql, whereSql, commodityInputView);
    }

    @Override
    public Map<String, Object> renderProductsAddIn(Integer cid){
        Map<String, Object> data = new HashMap<>(9, 1);
        data.put("categoryList", dictService.getDicts(IDBConstant.COMM_CATEGORY));
        data.put("styleList", dictService.getDicts(IDBConstant.COMM_STYLE));
        data.put("materialList", dictService.getDicts(IDBConstant.COMM_MATERIAL));
        data.put("sizeList", dictService.getDicts(IDBConstant.USER_SIZE));
        if(cid != null){
            data.put("commodity", getCommodityInfo(cid));
            data.put("commodityColor", getCommodityColorByCid(cid));
            data.put("commoditySizeList", getCommoditySizeList(cid));
            data.put("coverImg", resourceService.getResourceByParentId(cid, IDBConstant.RESOURCE_COMMODITY_IMG, 0));
            data.put("broadImgList", resourceService.getResourcesByParentId(cid, IDBConstant.RESOURCE_COMMODITY_IMG, 0));
        }
        return data;
    }

    @Override
    public void addUpdateCommodityIn(CommodityInfo commodityInfo, MultipartHttpServletRequest request) throws IOException{
        int cid = commodityInfo.getCid();
        int coid = commodityInfo.getCommodityColor().getCoid();
        Timestamp timestamp = DateUtil.getNowDate();
        CommodityColor commodityColor = commodityInfo.getCommodityColor();
        if(cid == 0) {
            commodityInfo.setCreateTime(timestamp);
            commodityInfo.setStatus(IDBConstant.LOGIC_STATUS_YES);
            commodityInfo.setHot(IDBConstant.LOGIC_STATUS_NO);
            commodityInfo.setNewly(IDBConstant.LOGIC_STATUS_NO);
            commodityInfo.setSaleCount(0);
            commodityInfo.setSeqNo(0);
            commodityInfo.setCouPrice(commodityInfo.getPrice());
            baseDao.save(commodityInfo, null);

            commodityColor.setCid(commodityInfo.getCid());
            commodityColor.setCreateTime(timestamp);
            baseDao.save(commodityColor, null);

            commodityColor.getCommoditySizes().stream().forEach((commoditySize -> {
                commoditySize.setCreateTime(timestamp);
                commoditySize.setLockStock(0);
                commoditySize.setCid(commodityInfo.getCid());
                commoditySize.setCoid(commodityColor.getCoid());
                baseDao.save(commoditySize, null);
            }));

            cid = commodityInfo.getCid();
            coid = commodityColor.getCoid();
        }else{
            CommodityInfo commodityInfoDB = getCommodityInfo(cid);
            commodityInfoDB.setCommName(commodityInfo.getCommName());
            commodityInfoDB.setCategory(commodityInfo.getCategory());
            commodityInfoDB.setStyle(commodityInfo.getStyle());
            commodityInfoDB.setMaterial(commodityInfo.getMaterial());
            commodityInfoDB.setPrice(commodityInfo.getPrice());
            commodityInfoDB.setProductDesc(commodityInfo.getProductDesc());
            commodityInfoDB.setUpdateTime(timestamp);
            baseDao.save(commodityInfoDB, cid);

            CommodityColor commodityColorDB = getCommodityColor(commodityColor.getCoid());
            commodityColorDB.setColorName(commodityColor.getColorName());
            commodityColorDB.setUpdateTime(timestamp);
            baseDao.save(commodityColorDB, commodityColorDB.getCoid());

            commodityColor.getCommoditySizes().stream().forEach((commoditySize -> {
                int sid = commoditySize.getSid();
                if (sid > 0) {
                    CommoditySize commoditySizeDB = getCommoditySize(sid);
                    commoditySizeDB.setUpdateTime(timestamp);
                    commoditySizeDB.setSize(commoditySize.getSize());
                    commoditySizeDB.setStock(commoditySize.getStock());
                    baseDao.save(commoditySizeDB, sid);
                } else {
                    commoditySize.setCreateTime(timestamp);
                    commoditySize.setLockStock(0);
                    commoditySize.setCid(commodityInfo.getCid());
                    commoditySize.setCoid(commodityColor.getCoid());
                    baseDao.save(commoditySize, null);
                }
            }));
        }
        //处理图片
        List<SysResources> sysResources = FileUtil.getSpringUpload(request, StrUtil.EMPTY);
        for(SysResources sysResource : sysResources){
            String name = sysResource.getName();
            sysResource.setResourceSeq(StrUtil.objToInt(name.substring(name.lastIndexOf(IPlatformConstant.UNDERLINE)+1)));
            sysResource.setResourceServiceParentId(cid);
            sysResource.setResourceServiceId(coid);
            sysResource.setResourceServiceType(IDBConstant.RESOURCE_COMMODITY_IMG);
            sysResource.setResourceType(IDBConstant.RESOURCE_TYPE_IMG);
            baseDao.save(sysResource, null);
        }
    }

    @Override
    public void deleteSizeIn(int sid){
        CommoditySize commoditySize = getCommoditySize(sid);
        baseDao.delete(commoditySize);
    }

    @Override
    public void updateCommodityStatusIn(int cid, String status){
        CommodityInfo commodityInfo = getCommodityInfo(cid);
        commodityInfo.setStatus(status);
        baseDao.save(commodityInfo, cid);
    }

    @Override
    public void updateCommodityHotIn(int cid, String hot){
        CommodityInfo commodityInfo = getCommodityInfo(cid);
        commodityInfo.setHot(hot);
        baseDao.save(commodityInfo, cid);
    }
    @Override
    public void updateCommodityNewlyIn(int cid, String newly){
        CommodityInfo commodityInfo = getCommodityInfo(cid);
        commodityInfo.setNewly(newly);
        baseDao.save(commodityInfo, cid);
    }

}











