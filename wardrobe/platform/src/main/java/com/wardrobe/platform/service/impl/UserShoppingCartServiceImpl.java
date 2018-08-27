package com.wardrobe.platform.service.impl;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.po.CommodityColor;
import com.wardrobe.common.po.CommodityInfo;
import com.wardrobe.common.po.CommoditySize;
import com.wardrobe.common.po.UserShoppingCart;
import com.wardrobe.common.util.Arith;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.CommodityInputView;
import com.wardrobe.platform.service.ICommodityService;
import com.wardrobe.platform.service.IUserShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cxs on 2018/8/24.
 */
@Service
public class UserShoppingCartServiceImpl extends BaseService implements IUserShoppingCartService {

    @Autowired
    private ICommodityService commodityService;

    @Override
    public synchronized void saveShoppingCart(UserShoppingCart userShoppingCart){
        Integer sid = userShoppingCart.getSid();
        Timestamp nowDate = DateUtil.getNowDate();
        UserShoppingCart userShoppingCartDB = getUserShoppingCartBySid(sid, userShoppingCart.getShoppingType());
        if(userShoppingCartDB == null) {
            CommoditySize commoditySize = commodityService.getCommoditySize(sid);
            CommodityColor commodityColor = commodityService.getCommodityColor(commoditySize.getCoid());
            CommodityInfo commodityInfo = commodityService.getCommodityInfo(commodityColor.getCid());
            userShoppingCart.setCoid(commodityColor.getCoid());
            userShoppingCart.setCid(commodityInfo.getCid());
            userShoppingCart.setCreateTime(nowDate);
            userShoppingCart.setCount(1);
            baseDao.save(userShoppingCart, null);
        }else{
            userShoppingCartDB.setCount(userShoppingCartDB.getCount()+1);
            userShoppingCartDB.setUpdateTime(nowDate);
            baseDao.save(userShoppingCartDB, userShoppingCartDB.getScid());
        }
    }

    private UserShoppingCart getUserShoppingCartBySid(int sid, String shoppingType){
        return baseDao.queryByHqlFirst("FROM UserShoppingCart WHERE sid = ?1 AND shoppingType = ?2", sid, shoppingType);
    }

    @Override
    public Map<String, Object> getUserShoppingCartList(CommodityInputView commodityInputView){
        Map<String, Object> data = new HashMap();

        PageBean pageBean = getUserShoppingCarts(commodityInputView);
        List<Map<String, Object>> list = pageBean.getList();
        double sumPrice = 0;
        for(Map<String, Object> map : list){
            sumPrice = Arith.add(sumPrice, Arith.mul(StrUtil.objToDouble(map.get("price")), StrUtil.objToInt(map.get("count"))));
        }

        data.put("pageBean", pageBean);
        data.put("sumPrice", sumPrice);
        return data;
    }

    private PageBean getUserShoppingCarts(CommodityInputView commodityInputView){
        Integer uid = commodityInputView.getUid();
        String shoppingType = commodityInputView.getShoppingType();

        StringBuilder headSql = new StringBuilder("SELECT usc.scid, usc.count, ci.commName, ci.price, cc.colorName, cs.size");
        StringBuilder bodySql = new StringBuilder(" FROM user_shopping_cart usc, commodity_size cs, commodity_color cc, commodity_info ci");
        StringBuilder whereSql = new StringBuilder(" WHERE usc.sid = cs.sid AND cs.coid = cc.coid AND cc.cid = ci.cid");
        if(uid != null){
            whereSql.append(" AND usc.uid = :uid");
        }
        if(StrUtil.isNotBlank(shoppingType)){
            whereSql.append(" AND usc.shoppingType = :shoppingType");
        }
        return super.getPageBean(headSql, bodySql, whereSql, commodityInputView);
    }

    @Override
    public void deleteUserShoppingCart(int scid, int userId){
        UserShoppingCart userShoppingCart = getUserShoppingCart(scid);
        if(userId == userShoppingCart.getUid()){
            baseDao.delete(userShoppingCart);
        }
    }

    @Override
    public UserShoppingCart getUserShoppingCart(int scid){
        return baseDao.getToEvict(UserShoppingCart.class, scid);
    }

}
