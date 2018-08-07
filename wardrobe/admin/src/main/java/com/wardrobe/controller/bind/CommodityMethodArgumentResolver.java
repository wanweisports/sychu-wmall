package com.wardrobe.controller.bind;

import com.wardrobe.common.po.CommodityInfo;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.controller.annotation.CommodityResolver;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

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
        String commodityJson = request.getParameter("commodity");
        CommodityInfo commodityInfo = JsonUtils.fromJson(commodityJson, CommodityInfo.class);
        return commodityInfo;
    }
}
