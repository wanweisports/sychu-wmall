package com.wardrobe.platform.service;

import com.wardrobe.common.bean.DiscountBean;
import com.wardrobe.common.po.UserShoppingCart;
import com.wardrobe.common.view.CommodityInputView;
import com.wardrobe.common.view.UserCouponInputView;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by cxs on 2018/8/24.
 */
public interface IUserShoppingCartService {

    void saveShoppingCart(UserShoppingCart userShoppingCart);

    Map<String, Object> getUserShoppingCartList(CommodityInputView commodityInputView);

    void deleteUserShoppingCart(String scids, int userId);

    UserShoppingCart getUserShoppingCart(int scid);

    Map<String, Object> settlement(String scids, int uid) throws ParseException;

    double countSumPrice(List<Map<String, Object>> list);

    Map<String, Object> settlementCount(UserCouponInputView userCouponInputView, int uid) throws ParseException;

    Map<String, Object> settlementRfidCount(UserCouponInputView userCouponInputView, int uid) throws ParseException;

    DiscountBean concessionalPrice(double sumPrice, String serviceType, Integer cpid, int uid, Integer commodityCount) throws ParseException;

}
