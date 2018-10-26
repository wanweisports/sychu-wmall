package com.wardrobe.interceptor;

import com.wardrobe.common.annotation.NotPerfect;
import com.wardrobe.common.annotation.NotProtected;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.po.UserInfo;
import com.wardrobe.common.util.RequestUtil;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class AuthorizationHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    private Logger logger = Logger.getLogger(AuthorizationHandlerInterceptor.class);
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Object userInfo = request.getSession().getAttribute(IPlatformConstant.LOGIN_USER);

        // 未标记不受保护权限的Controller和方法，在没有登录的情况下跳转至登录页面
        NotProtected classAnnotation = handlerMethod.getBeanType().getAnnotation(NotProtected.class);
    	NotProtected methodAnnotation = handlerMethod.getMethod().getAnnotation(NotProtected.class);
        if (classAnnotation == null && methodAnnotation == null) { //如果受保护
            if (userInfo == null) {
                logger.error("=========未登录，跳到/notLogin接口===========：访问：" + request.getRequestURI() + ":" + request.getQueryString());
                request.getRequestDispatcher("/notLogin").forward(request, response); //未登录---跳到统一接口：返回未登录状态10
                return false;
            }
        }else{
            return true; //有NotProtected注解的都通过
        }

        //未完善资料
        if(userInfo != null) {
            NotPerfect classAnnotation2 = handlerMethod.getBeanType().getAnnotation(NotPerfect.class);
            NotPerfect methodAnnotation2 = handlerMethod.getMethod().getAnnotation(NotPerfect.class);
            if (classAnnotation2 == null && methodAnnotation2 == null) { //如果受保护
                if (!userService.userIsPerfect(((UserInfo) userInfo).getUid())) {
                    logger.error("=========未完善资料，跳到/notPerfect接口===========：访问：" + request.getRequestURI() + ":" + request.getQueryString());
                    request.getRequestDispatcher("/notPerfect").forward(request, response); //未完善资料---跳到统一接口：返回未完善资料状态20
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //request.setAttribute("static_resource_version", siteVersionHolder.getVersion());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
