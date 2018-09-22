package com.wardrobe.controller;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.po.UserShoppingCart;
import com.wardrobe.common.view.CommodityInputView;
import com.wardrobe.platform.service.ICommodityService;
import com.wardrobe.platform.service.IUserShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by cxs on 2018/7/30.
 */
@RequestMapping("commodity")
@Controller
public class CommodityController extends BaseController {

    @Autowired
    private ICommodityService commodityService;

    @Autowired
    private IUserShoppingCartService userShoppingCartService;

    @ResponseBody
    @RequestMapping("index")
    public ResponseBean index(CommodityInputView commodityInputView){
        return new ResponseBean(super.setPageInfo(commodityService.getCommodityList(commodityInputView)));
    }

    @ResponseBody
    @RequestMapping("detail")
    public ResponseBean index(int cid){
        return new ResponseBean(commodityService.getCommodityDetail(cid, getUserInfo().getUid()));
    }

    @ResponseBody
    @RequestMapping("/detail/selected")
    public ResponseBean detailSelected(int cid){
        return new ResponseBean(commodityService.getCommodityDetailSelected(cid));
    }

    @ResponseBody
    @RequestMapping("saveShoppingCart")
    public ResponseBean saveShoppingCart(UserShoppingCart userShoppingCart){
        userShoppingCart.setUid(getUserInfo().getUid());
        userShoppingCartService.saveShoppingCart(userShoppingCart);
        return new ResponseBean(true);
    }

    @ResponseBody
    @RequestMapping("userShoppingCarts")
    public ResponseBean userShoppingCarts(CommodityInputView commodityInputView){
        commodityInputView.setUid(getUserInfo().getUid());
        Map<String, Object> data = userShoppingCartService.getUserShoppingCartList(commodityInputView);
        data.putAll(setPageInfo((PageBean) data.get("pageBean")));
        data.remove("pageBean");
        return new ResponseBean(data);
    }

    @ResponseBody
    @RequestMapping("delShoppingCart")
    public ResponseBean delShoppingCart(String scids){
        userShoppingCartService.deleteUserShoppingCart(scids, getUserInfo().getUid());
        return new ResponseBean(true);
    }

    @ResponseBody
    @RequestMapping("settlement")
    public ResponseBean settlement(String scids){
        return new ResponseBean(userShoppingCartService.settlement(scids, getUserInfo().getUid()));
    }

    @ResponseBody
    @RequestMapping("commodityBanners")
    public ResponseBean commodityBanners(){
        return new ResponseBean(commodityService.getCommodityBanners());
    }

}
