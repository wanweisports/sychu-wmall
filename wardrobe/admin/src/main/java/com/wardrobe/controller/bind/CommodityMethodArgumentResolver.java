package com.wardrobe.controller.bind;

import com.wardrobe.common.po.CommodityColor;
import com.wardrobe.common.po.CommodityInfo;
import com.wardrobe.common.po.CommoditySize;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.controller.annotation.CommodityResolver;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxs on 2018/8/7.
 */
public class CommodityMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(CommodityResolver.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String commodityJson = request.getParameter("json");
        CommodityInfo commodityInfo = JsonUtils.fromJson(commodityJson, CommodityInfo.class);
        CommodityColor commodityColor = JsonUtils.fromJson(commodityJson, CommodityColor.class);

        String[] sids = request.getParameterValues("sid");
        String[] sizes = request.getParameterValues("size");
        String[] stocks = request.getParameterValues("stock");

        List<CommoditySize> commoditySizes = new ArrayList<>();
        int i = 0;
        for(String size : sizes){
            CommoditySize commoditySize = new CommoditySize();
            commoditySize.setSize(size);
            commoditySize.setStock(StrUtil.objToInt(stocks[i]));
            if(sids != null && i < sids.length){
                commoditySize.setSid(StrUtil.objToInt(sids[i]));
            }
            commoditySizes.add(commoditySize);
            i++;
        }
        commodityColor.setCommoditySizes(commoditySizes);
        commodityInfo.setCommodityColor(commodityColor);
        return commodityInfo;
    }
}
