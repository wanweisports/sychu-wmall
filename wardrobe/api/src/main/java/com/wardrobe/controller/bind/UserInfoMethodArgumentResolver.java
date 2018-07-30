package com.wardrobe.controller.bind;

import com.wardrobe.common.bean.UserPerfectBean;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.controller.annotation.UserPerfect;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cxs on 2018/7/30.
 */
public class UserInfoMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(UserPerfect.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String userJson = request.getParameter("user");
        UserPerfectBean userPerfectBean = JsonUtils.fromJson(userJson, UserPerfectBean.class);
        return userPerfectBean;
    }
}
