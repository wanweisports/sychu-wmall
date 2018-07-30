package com.wardrobe.interceptor;

import com.wardrobe.common.annotation.NotProtected;
import com.wardrobe.common.constant.IPlatformConstant;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.wardrobe.interceptor.InterceptorHelp.NOT_LOGIN;
import static com.wardrobe.interceptor.InterceptorHelp.isAjax;

public class AuthorizationHandlerInterceptor implements HandlerInterceptor {
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
    	//if(1==1) return true; //方便测试，后期删除！
        	
    	if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 未标记不受保护权限的Controller和方法，在没有登录的情况下跳转至登录页面
        /*NotProtected classAnnotation = handlerMethod.getBeanType().getAnnotation(NotProtected.class);
    	NotProtected methodAnnotation = handlerMethod.getMethod().getAnnotation(NotProtected.class);
        if (classAnnotation == null && methodAnnotation == null) {
            if (request.getSession().getAttribute(IPlatformConstant.LOGIN_USER) == null) {
                if(isAjax(request)){
                    PrintWriter out = response.getWriter();
                    out.write(NOT_LOGIN); //这里必须是数字, 不能是字符串(字符串会解析成js变量)
                    out.close();
                    return false;
                }else {
                    response.sendRedirect("/notLogin"); //未登录---跳到统一接口：返回未登录json
                	*//*request.getRequestDispatcher("/login?returnUrl="+RequestUtil.getRequestURIQuery(request)).forward(request, response);*//*
                    return false;// 终止拦截器继续传播
                }
            }
        }*/
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
