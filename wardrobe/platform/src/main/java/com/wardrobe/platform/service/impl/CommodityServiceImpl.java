package com.wardrobe.platform.service.impl;

import com.wardrobe.aliyun.OssClient;
import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.*;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.FileUtil;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.CommodityInputView;
import com.wardrobe.platform.service.ICommodityService;
import com.wardrobe.platform.service.IResourceService;
import com.wardrobe.platform.service.IUserService;
import com.wardrobe.platform.service.IXlsExportImportService;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.ByteArrayInputStream;
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

    @Autowired
    private IUserService userService;

    @Autowired
    private IXlsExportImportService xlsExportImportService;

    @Override
    public PageBean getCommodityList(CommodityInputView commodityInputView){
        Integer uid = commodityInputView.getUid();
        PageBean pageBean = getCommoditys(commodityInputView);
        List<Map<String, Object>> list = pageBean.getList();
        list.stream().forEach((commodity) ->{
            Integer cid = StrUtil.objToInt(commodity.get("cid"));
            commodity.put("resourcePath", getFmImg(cid));//0表示封面图
            commodity.put("collection", userService.getUserCollection(cid, uid) != null ? IDBConstant.LOGIC_STATUS_YES : IDBConstant.LOGIC_STATUS_NO);
        });
        return pageBean;
    }

    private PageBean getCommoditys(CommodityInputView commodityInputView){
        String category = commodityInputView.getCategory(); //regexp ',1,|,333,'
        String style = commodityInputView.getStyle();       //regexp ',1,|,333,'
        String material = commodityInputView.getMaterial(); //regexp ',1,|,333,'
        String newly = commodityInputView.getNewly();
        String hot = commodityInputView.getHot();
        Integer groupId = commodityInputView.getGroupId();
        String orderBy = commodityInputView.getOrderBy();

        StringBuilder headSql = new StringBuilder("SELECT ci.cid, ci.commName, ci.price, ci.couPrice, ci.brandName");
        StringBuilder bodySql = new StringBuilder(" FROM commodity_info ci");
        StringBuilder whereSql = new StringBuilder(" WHERE ci.status = '").append(IDBConstant.LOGIC_STATUS_YES).append("'"); //小程序前端只查询上架商品
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
        if(groupId != null){
            whereSql.append(" AND ci.groupId = :groupId");
        }
        whereSql.append(" ORDER BY ");
        if(StrUtil.isNotBlank(orderBy)){
            whereSql.append(orderBy).append(",");
        }else {
            whereSql.append(" ci.seqNo DESC, ci.createTime DESC");
        }
        return super.getPageBean(headSql, bodySql, whereSql, commodityInputView);
    }

    @Override
    public Map<String, Object> getCommodityDetail(int cid, int uid){
        Map<String, Object> data = new HashMap<>(9, 1);
        CommodityInfo commodityInfo = getCommodityInfo(cid);
        data.put("sizes", getCommoditySizeNames(cid));
        data.put("colors", getCommodityColors(commodityInfo.getGroupId()));
        data.put("commName", commodityInfo.getCommName());
        data.put("brandName", commodityInfo.getBrandName());
        data.put("price", commodityInfo.getPrice());
        data.put("couPrice", commodityInfo.getCouPrice());
        data.put("desc", commodityInfo.getProductDesc());
        data.put("resources", resourceService.getResourcesPath(resourceService.getResourcesByParentId(cid, IDBConstant.RESOURCE_COMMODITY_IMG)));
        data.put("collection", userService.getUserCollection(cid, uid) != null ? IDBConstant.LOGIC_STATUS_YES : IDBConstant.LOGIC_STATUS_NO);
        return data;
    }

    @Override
    public Map<String, Object> getCommodityDetailSelected(int cid){
        Map<String, Object> data = new HashMap<>(7, 1);
        CommodityInfo commodityInfo = getCommodityInfo(cid);
        data.put("sizes", getCommoditySizeList(cid));
        data.put("cid", commodityInfo.getCid());
        data.put("commName", commodityInfo.getCommName());
        data.put("price", commodityInfo.getPrice());
        data.put("couPrice", commodityInfo.getCouPrice());
        data.put("resourcePath", getFmImg(commodityInfo.getCid())); //0表示封面图
        return data;
    }

    @Override
    public String getFmImg(int cid){
        return getFmImg(cid, true);
    }

    @Override
    public String getFmImg(int cid, boolean isOss){
        SysResources sysResources = resourceService.getResourceByParentId(cid, IDBConstant.RESOURCE_COMMODITY_IMG, 0, isOss);  //0表示封面图
        return sysResources != null ? sysResources.getResourcePath() : StrUtil.EMPTY;
    }

    private SysResources getFmImgObj(int cid){
        return resourceService.getResourceByParentId(cid, IDBConstant.RESOURCE_COMMODITY_IMG, 0);  //0表示封面图
    }

    @Override
    public CommodityInfo getCommodityInfo(int cid){
        return baseDao.getToEvict(CommodityInfo.class, cid);
    }

    @Override
    public CommodityColor getCommodityColor(int coid){
        return baseDao.getToEvict(CommodityColor.class, coid);
    }

    @Override
    public CommoditySize getCommoditySize(int sid){
        return baseDao.getToEvict(CommoditySize.class, sid);
    }

    @Override
    public CommodityColor getCommodityColorByCid(int cid){
        return baseDao.queryByHqlFirst("FROM CommodityColor WHERE cid = ?1", cid);
    }

    private List<Map<String, Object>> getCommodityColors(int groupId){
        return baseDao.queryBySql("SELECT cc.cid, cc.colorName FROM commodity_info ci, commodity_color cc WHERE ci.cid = cc.cid AND ci.groupId = ?1 AND ci.status = ?2", groupId, IDBConstant.LOGIC_STATUS_YES);
    }

    private String[] getCommoditySizeNames(int cid){
        String sizes = StrUtil.objToStr(baseDao.getUniqueObjectResult("SELECT GROUP_CONCAT(size) FROM commodity_size WHERE cid = ?1", cid));
        return sizes.split(",");
    }

    @Override
    public List<CommoditySize> getCommoditySizeList(int cid){
        return baseDao.queryByHql("FROM CommoditySize WHERE cid = ?1", cid);
    }

    @Override
    public PageBean getCommodityListIn(CommodityInputView commodityInputView){
        PageBean pageBean = getCommoditysIn(commodityInputView);
        List<Map<String, Object>> list = pageBean.getList();
        list.stream().forEach((commodity) -> {
            Integer cid = StrUtil.objToInt(commodity.get("cid"));
            getType(commodity);
            commodity.put("statusName", dictService.getDict(IDBConstant.COMM_STATUS, StrUtil.objToStr(commodity.get("status"))).getDictValue());
            commodity.put("resourcePath", getFmImg(cid));//0表示封面图
            commodity.put("collectionCount", getCollectionCount(cid));
        });
        return pageBean;
    }

    private int getCollectionCount(int cid){
        return baseDao.getUniqueResult("SELECT COUNT(1) FROM user_collection uc WHERE uc.cid = ?1", cid).intValue();
    }

    private Map<String, Object> getType(Map<String, Object> commodity){
        String category = StrUtil.objToStr(commodity.get("category"));
        String style = StrUtil.objToStr(commodity.get("style"));
        String material = StrUtil.objToStr(commodity.get("material"));
        commodity.put("categoryName", getTypes(category, IDBConstant.COMM_CATEGORY));
        commodity.put("styleName", getTypes(style, IDBConstant.COMM_STYLE));
        commodity.put("materialName", getTypes(material, IDBConstant.COMM_MATERIAL));
        return commodity;
    }

    private PageBean getCommoditysIn(CommodityInputView commodityInputView){
        String newly = commodityInputView.getNewly();
        String hot = commodityInputView.getHot();
        String commName = commodityInputView.getCommName();
        String status = commodityInputView.getStatus();
        Integer groupId = commodityInputView.getGroupId();
        String commNo = commodityInputView.getCommNo();

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
            whereSql.append(" AND ci.commName LIKE :commName");
            commodityInputView.setCommName("%" + commName + "%");
        }
        if(StrUtil.isNotBlank(commNo)){
            whereSql.append(" AND ci.commNo = :commNo");
        }
        if(StrUtil.isNotBlank(status)){
            whereSql.append(" AND ci.status = :status");
        }
        if(groupId != null){
            whereSql.append(" AND ci.groupId = :groupId");
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
            data.put("coverImg", getFmImgObj(cid));
            data.put("broadImgList", resourceService.getResourcesByParentId(cid, IDBConstant.RESOURCE_COMMODITY_IMG, 0));
        }
        return data;
    }

    @Override
    public void addUpdateCommodityIn(CommodityInfo commodityInfo, String resourceIds, MultipartHttpServletRequest request) throws Exception{
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
            baseDao.save(commodityInfo, null);

            int newCid = commodityInfo.getCid();
            if(commodityInfo.getGroupId() == null) {
                commodityInfo.setGroupId(newCid);
                baseDao.save(commodityInfo, newCid);
            }

            commodityColor.setCid(newCid);
            commodityColor.setCreateTime(timestamp);
            baseDao.save(commodityColor, null);

            commodityColor.getCommoditySizes().stream().forEach((commoditySize -> {
                commoditySize.setCreateTime(timestamp);
                commoditySize.setLockStock(0);
                commoditySize.setCid(newCid);
                commoditySize.setCoid(commodityColor.getCoid());
                baseDao.save(commoditySize, null);

                //保存库存留痕
                this.saveStock(new CommodityStock(commoditySize.getSid(), IDBConstant.COMM_STOCK_TYPE_ADD, commoditySize.getStock(), "入库", 0), false);
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
            commodityInfoDB.setCouPrice(commodityInfo.getCouPrice());
            commodityInfoDB.setProductDesc(commodityInfo.getProductDesc());
            commodityInfoDB.setCommNo(commodityInfo.getCommNo());
            commodityInfoDB.setBrandName(commodityInfo.getBrandName());
            commodityInfoDB.setSeqNo(commodityInfo.getSeqNo());
            commodityInfoDB.setUpdateTime(timestamp);
            baseDao.save(commodityInfoDB, cid);

            CommodityColor commodityColorDB = getCommodityColor(commodityColor.getCoid());
            commodityColorDB.setColorName(commodityColor.getColorName());
            commodityColorDB.setUpdateTime(timestamp);
            baseDao.save(commodityColorDB, commodityColorDB.getCoid());

            List<CommoditySize> commoditySizes = commodityColor.getCommoditySizes();
            if(!CollectionUtils.isEmpty(commoditySizes)) {
                commoditySizes.stream().forEach((commoditySize -> {
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
        }
        //删除老图片
        if (resourceIds != null && !resourceIds.isEmpty()) {
            resourceIds = resourceIds != null ? resourceIds : StrUtil.EMPTY; //resourceIds前端删除的图片id
            List<SysResources> oldResources = resourceService.getExistIds(resourceIds, cid, IDBConstant.RESOURCE_COMMODITY_IMG);
            for(SysResources sysResource : oldResources){
                baseDao.delete(sysResource);
            }
        }

        //处理图片
        List<SysResources> sysResources = FileUtil.getSpringUpload(request, OssClient.OSS_IMG_PATH);
        for(SysResources sysResource : sysResources){
            //保存到阿里云oss
            byte[] bytes = IOUtils.toByteArray(sysResource.getInputStream());
            OssClient.putInputStreamYS(new ByteArrayInputStream(bytes), sysResource.getResourcePath()); //保存压缩图
            OssClient.putInputStream(new ByteArrayInputStream(bytes), sysResource.getResourcePathOG()); //保存原图

            //保存到数据库
            String name = sysResource.getName();
            sysResource.setResourceSeq(StrUtil.objToInt(name.substring(name.lastIndexOf(IPlatformConstant.UNDERLINE) + 1)));
            sysResource.setResourceServiceParentId(cid);
            sysResource.setResourceServiceId(coid);
            sysResource.setResourceServiceType(IDBConstant.RESOURCE_COMMODITY_IMG);
            sysResource.setResourceType(IDBConstant.RESOURCE_TYPE_IMG);
            baseDao.save(sysResource, null);
        }
    }

    @Override
    public void addSizeIn(CommoditySize commoditySize){
        if(StrUtil.isBlank(commoditySize.getSize())) throw new MessageException("请选择尺码!");
        if(commoditySize.getStock() == null) throw new MessageException("请输入库存!");

        Timestamp nowDate = DateUtil.getNowDate();
        CommodityColor commodityColor = getCommodityColorByCid(commoditySize.getCid());
        commoditySize.setCoid(commodityColor.getCoid());
        commoditySize.setLockStock(0);
        commoditySize.setCreateTime(nowDate);
        baseDao.save(commoditySize, null);

        //保存库存留痕
        this.saveStock(new CommodityStock(commoditySize.getSid(), IDBConstant.COMM_STOCK_TYPE_ADD, commoditySize.getStock(), "入库", 0), false);
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

    @Override
    public Map<String, Object> renderCommodityDetailIn(int cid){
        Map<String, Object> data = new HashMap<>(7, 1);
        CommodityInfo commodityInfo = getCommodityInfo(cid);
        Integer groupId = commodityInfo.getGroupId();
        data.put("product", getType(JsonUtils.fromJson(commodityInfo)));
        data.put("productSizeList", getCommoditySizeList(cid));
        data.put("coverImg", getFmImgObj(cid));
        data.put("broadImgList", resourceService.getResourcesByParentId(cid, IDBConstant.RESOURCE_COMMODITY_IMG, 0));
        if(groupId != null) {
            data.put("groupCommodityColorList", getCommodityColorByGroupId(groupId));
        }
        data.put("sizeList", dictService.getDicts(IDBConstant.USER_SIZE));

        CommodityBanner commodityBanner = getCommodityBanner(cid);
        if(commodityBanner != null) {
            data.put("commodityBanner", commodityBanner);
            data.put("bannerImg", resourceService.getResource(cid, IDBConstant.RESOURCE_COMMODITY_BANNER_IMG));
        }
        return data;
    }

    private List<Map<String, Object>> getCommodityColorByGroupId(int groupId){
        return baseDao.queryBySql("SELECT cc.* FROM commodity_info ci, commodity_color cc WHERE ci.cid = cc.cid AND ci.groupId = ?1", groupId);
    }

    @Override
    public synchronized void saveStock(CommodityStock commodityStock, boolean add){
        CommoditySize commoditySize = getCommoditySize(commodityStock.getSid());
        if(add) {
            if(IDBConstant.COMM_STOCK_TYPE_SUB.equals(commodityStock.getType())) {
                commoditySize.setStock(commoditySize.getStock() - commodityStock.getNum());
                if (commoditySize.getStock() < 0) throw new MessageException("库存少于0，不能操作！");
            }else{
                commoditySize.setStock(commoditySize.getStock() + commodityStock.getNum());
            }
            baseDao.save(commoditySize, commoditySize.getSid());
        }
        baseDao.save(commodityStock, null);
    }

    @Override
    public void saveOrderSubStock(UserOrderInfo userOrderInfo){
        List<UserOrderDetail> userOrderDetails = userOrderInfo.getUserOrderDetails();
        if(userOrderDetails != null){
            Timestamp nowDate = DateUtil.getNowDate();
            userOrderDetails.stream().forEach(userOrderDetail -> {
                CommoditySize commoditySize = getCommoditySize(userOrderDetail.getSid());
                if (commoditySize != null) {
                    CommodityStock commodityStock = new CommodityStock();
                    commodityStock.setSid(userOrderDetail.getSid());
                    commodityStock.setNum(userOrderDetail.getItemCount());
                    commodityStock.setCreateTime(nowDate);
                    commodityStock.setOperatorId(userOrderInfo.getUid());
                    commodityStock.setType(IDBConstant.COMM_STOCK_TYPE_SALE);
                    commodityStock.setRemark("购买");
                    baseDao.save(commodityStock, null);
                }
            });
        }
    }

    @Desc("写入商品销售总数")
    @Override
    public void saveCommoditySaleCount(UserOrderInfo userOrderInfo){
        List<UserOrderDetail> userOrderDetails = userOrderInfo.getUserOrderDetails();
        if(userOrderDetails != null){
            for(UserOrderDetail userOrderDetail : userOrderDetails){
                CommodityInfo commodityInfo = getCommodityInfo(userOrderDetail.getCid());
                commodityInfo.setSaleCount(commodityInfo.getSaleCount() + userOrderDetail.getItemCount());
                baseDao.save(commodityInfo, commodityInfo.getCid());
            }
        }
    }

    @Override
    public PageBean getStockListIn(CommodityInputView commodityInputView){
        PageBean pageBean = getStocks(commodityInputView);
        List<Map<String, Object>> list = pageBean.getList();
        list.stream().forEach(commodity -> {
            commodity.put("resourcePath", getFmImg(StrUtil.objToInt(commodity.get("cid"))));//0表示封面图
            commodity.put("typeName", dictService.getDict(IDBConstant.COMM_STOCK_TYPE, StrUtil.objToStr(commodity.get("type"))).getDictValue());
        });
        return pageBean;
    }

    private PageBean getStocks(CommodityInputView commodityInputView){
        String type = commodityInputView.getType();
        Integer commId = commodityInputView.getCommId();
        String startTime = commodityInputView.getStartTime();
        String endTime = commodityInputView.getEndTime();

        StringBuilder headSql = new StringBuilder("SELECT ct.*, cs.size, cc.colorName, ci.commName, ci.cid");
        StringBuilder bodySql = new StringBuilder(" FROM commodity_stock ct, commodity_size cs, commodity_color cc, commodity_info ci");
        StringBuilder whereSql = new StringBuilder(" WHERE ct.sid = cs.sid AND cs.coid = cc.coid AND cc.cid = ci.cid");
        if(StrUtil.isNotBlank(type)){
            whereSql.append(" AND ct.type = :type");
        }
        /*if(commId != null){
            whereSql.append(" AND ci.cid = :commId");
        }*/
        if(StrUtil.isNotBlank(startTime)){
            whereSql.append(" AND ct.createTime >= :startTime");
        }
        if(StrUtil.isNotBlank(endTime)){
            whereSql.append(" AND ct.createTime <= :endTime");
        }
        whereSql.append(" ORDER BY ct.createTime DESC");
        return super.getPageBean(headSql, bodySql, whereSql, commodityInputView);
    }

    @Override
    public void saveCommodityBanner(CommodityBanner commodityBanner, MultipartHttpServletRequest multipartRequest) throws IOException{
        if(getCommodityBanner(commodityBanner.getCid()) != null) return;
        commodityBanner.setCreateTime(DateUtil.getNowDate());
        baseDao.save(commodityBanner, null);

        //处理图片
        List<SysResources> sysResources = FileUtil.getSpringUpload(multipartRequest, OssClient.OSS_IMG_PATH);
        for(SysResources sysResource : sysResources){
            //保存到阿里云oss
            OssClient.putInputStream(sysResource.getInputStream(), sysResource.getResourcePath());
            //保存到数据库
            sysResource.setResourceSeq(commodityBanner.getSeqNo());
            sysResource.setResourceServiceParentId(commodityBanner.getCid());
            sysResource.setResourceServiceId(commodityBanner.getCid());
            sysResource.setResourceServiceType(IDBConstant.RESOURCE_COMMODITY_BANNER_IMG);
            sysResource.setResourceType(IDBConstant.RESOURCE_COMMODITY_IMG);
            baseDao.save(sysResource, null);
        }

    }

    @Override
    public void deleteCommodityBanner(int cid){
        baseDao.delete(getCommodityBanner(cid));
        SysResources resource = resourceService.getResource(cid, IDBConstant.RESOURCE_COMMODITY_BANNER_IMG);
        if(resource != null){
            baseDao.delete(resource);
        }
    }

    private CommodityBanner getCommodityBanner(int cid){
        return baseDao.queryByHqlFirst("FROM CommodityBanner WHERE cid = ?1", cid);
    }

    @Override
    public Map<String, Object> getCommodityBanners(){
        List<Map<String, Object>> list = baseDao.queryBySql("SELECT ci.cid, ci.commName FROM commodity_banner cb, commodity_info ci WHERE cb.cid = ci.cid ORDER BY cb.seqNo DESC, cb.createTime DESC");
        list.parallelStream().forEach(map -> {
            map.put("resourcePath", resourceService.getResourcePath(StrUtil.objToInt(map.get("cid")), IDBConstant.RESOURCE_COMMODITY_BANNER_IMG));
        });
        Map<String, Object> data = new HashMap<>(1, 1);
        data.put("list", list);
        return data;
    }

    @Override
    public PageBean getBannerCommodityListIn(CommodityInputView commodityInputView){
        PageBean pageBean = getBannerCommoditysIn(commodityInputView);
        List<Map<String, Object>> list = pageBean.getList();
        list.stream().forEach((commodity) -> {
            getType(commodity);
            commodity.put("statusName", dictService.getDict(IDBConstant.COMM_STATUS, StrUtil.objToStr(commodity.get("status"))).getDictValue());
            commodity.put("resourcePath", getFmImg(StrUtil.objToInt(commodity.get("cid"))));//0表示封面图
        });
        return pageBean;
    }

    private PageBean getBannerCommoditysIn(CommodityInputView commodityInputView){
        String newly = commodityInputView.getNewly();
        String hot = commodityInputView.getHot();
        String commName = commodityInputView.getCommName();
        String status = commodityInputView.getStatus();
        Integer groupId = commodityInputView.getGroupId();

        StringBuilder headSql = new StringBuilder("SELECT ci.*");
        StringBuilder bodySql = new StringBuilder(" FROM commodity_banner cb, commodity_info ci");
        StringBuilder whereSql = new StringBuilder(" WHERE cb.cid = ci.cid");
        if(IDBConstant.LOGIC_STATUS_YES.equals(newly)){
            whereSql.append(" AND ci.newly = :newly");
        }
        if(IDBConstant.LOGIC_STATUS_YES.equals(hot)){
            whereSql.append(" AND ci.hot = :hot");
        }
        if(StrUtil.isNotBlank(commName)){
            whereSql.append(" AND ci.commName LIKE :commName");
            commodityInputView.setCommName("%" + commName + "%");
        }
        if(StrUtil.isNotBlank(status)){
            whereSql.append(" AND ci.status = :status");
        }
        if(groupId != null){
            whereSql.append(" AND ci.groupId = :groupId");
        }
        whereSql.append(" ORDER BY ci.seqNo DESC, ci.createTime DESC");

        return super.getPageBean(headSql, bodySql, whereSql, commodityInputView);
    }

    @Override
    public void saveExcelCommoditys(MultipartHttpServletRequest multipartRequest) throws IOException{
        List<MultipartFile> fileList = FileUtil.springUpload(multipartRequest);
        if(fileList.size() == 0) throw new MessageException("请上传Excel文件!");
        Workbook workBook = xlsExportImportService.getWorkbook(fileList.get(0).getInputStream(), IPlatformConstant.excelExtensionX);
        List<Map<String, Object>> list0 = xlsExportImportService.readExcelData(workBook.getSheetAt(0));
        List<Map<String, Object>> list1 = xlsExportImportService.readExcelData(workBook.getSheetAt(1));

        Timestamp nowDate = DateUtil.getNowDate();

        Number number = baseDao.getUniqueResult("SELECT MAX(cid) FROM commodity_info");
        int maxCid = 10;
        if(number != null){
            maxCid = number.intValue()+10; //预留10个id位
        }
        for(Map<String, Object> map : list0){
            CommodityInfo commodityInfo = JsonUtils.fromJson(map, CommodityInfo.class);

            Number categoryId = dictService.getDictIdByValue(StrUtil.trim(commodityInfo.getCategory()), IDBConstant.COMM_CATEGORY);
            if(categoryId == null) throw new MessageException(commodityInfo.getCommName()+"，品类错误：" + commodityInfo.getCidMapping());

            commodityInfo.setCategory(IPlatformConstant.DOU_HAO + categoryId.toString() + IPlatformConstant.DOU_HAO);

            Number styleId = dictService.getDictIdByValue(StrUtil.trim(commodityInfo.getStyle()), IDBConstant.COMM_STYLE);
            if(styleId == null) throw new MessageException(commodityInfo.getCommName()+"，风格错误：" + commodityInfo.getCidMapping());
            commodityInfo.setStyle(IPlatformConstant.DOU_HAO + styleId.toString() + IPlatformConstant.DOU_HAO);

            Number materialId = dictService.getDictIdByValue(StrUtil.trim(commodityInfo.getMaterial()), IDBConstant.COMM_MATERIAL);
            if(materialId == null) throw new MessageException(commodityInfo.getCommName()+"，材质错误：" + commodityInfo.getCidMapping());
            commodityInfo.setMaterial(IPlatformConstant.DOU_HAO + materialId.toString() + IPlatformConstant.DOU_HAO);

            int autoCid = commodityInfo.getCidMapping() + maxCid;
            commodityInfo.setCouPrice(commodityInfo.getPrice());
            commodityInfo.setCreateTime(nowDate);
            commodityInfo.setGroupId(commodityInfo.getGroupId() + maxCid);
            //baseDao.save(commodityInfo, autoCid);
            baseDao.updateByJdbc("INSERT INTO `commodity_info` (`cid`, `commName`, `category`, `style`, `material`, `productDesc`, `price`, `couPrice`, `saleCount`, `status`, `seqNo`, `newly`, `hot`, `groupId`, `commNo`, `brandName`, `createTime`, `updateTime`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", autoCid, commodityInfo.getCommName(), commodityInfo.getCategory(), commodityInfo.getStyle(), commodityInfo.getMaterial(), commodityInfo.getProductDesc(), commodityInfo.getPrice(), commodityInfo.getCouPrice(), commodityInfo.getSaleCount(), commodityInfo.getStatus(), commodityInfo.getSeqNo(), commodityInfo.getNewly(), commodityInfo.getHot(), commodityInfo.getGroupId(), commodityInfo.getCommNo(), commodityInfo.getBrandName(), commodityInfo.getCreateTime(), null);

            CommodityColor commodityColor = new CommodityColor();
            commodityColor.setCid(autoCid);
            commodityColor.setColorName(commodityInfo.getColor());
            commodityColor.setCreateTime(nowDate);
            baseDao.save(commodityColor, null);
            List<Map<String, Object>> sizes = getCommoditySizes(commodityInfo.getCidMapping(), list1);
            sizes.stream().forEach(sizeMap -> {
                CommoditySize commoditySize = JsonUtils.fromJson(sizeMap, CommoditySize.class);
                commoditySize.setCreateTime(nowDate);
                commoditySize.setCoid(commodityColor.getCoid());
                commoditySize.setCid(autoCid);
                baseDao.save(commoditySize, null);
            });
        }

    }

    private List<Map<String, Object>> getCommoditySizes(int cidMapping, List<Map<String, Object>> list1){
        List<Map<String, Object>> sizes = new ArrayList<>();
        for(Map<String, Object> map : list1){
            if(StrUtil.objToInt(map.get("cidMapping")) == cidMapping){
                sizes.add(map);
            }
        }
        return sizes;
    }

    @Override
    public void deletecCommodity(int cid){
        CommodityInfo commodityInfo = getCommodityInfo(cid);
        CommodityColor commodityColor = getCommodityColorByCid(cid);
        List<CommoditySize> commoditySizes = getCommoditySizeList(cid);

        List<SysResources> resources = resourceService.getResourcesByParentId(cid, IDBConstant.RESOURCE_COMMODITY_IMG);
        List<SysResources> resourceBanners = resourceService.getResourcesByParentId(cid, IDBConstant.RESOURCE_COMMODITY_BANNER_IMG);
        if(commodityInfo != null){
            baseDao.delete(commodityInfo);
        }
        if(commodityColor != null){
            baseDao.delete(commodityColor);
        }
        if(commoditySizes != null && commoditySizes.size() > 0){
            commoditySizes.stream().forEach(commoditySize -> {
                baseDao.delete(commoditySize);
            });
        }
        if(resources != null && resources.size() > 0){
            resources.stream().forEach(resource -> {
                baseDao.delete(resource);
            });
        }
        if(resourceBanners != null && resourceBanners.size() > 0){
            resourceBanners.stream().forEach(resource -> {
                baseDao.delete(resource);
            });
        }
    }

    @Override
    public void updateCommoditySeqNo(int cid, int seqNo){
        CommodityInfo commodityInfo = getCommodityInfo(cid);
        commodityInfo.setSeqNo(seqNo);
        baseDao.save(commodityInfo, cid);
    }

}











