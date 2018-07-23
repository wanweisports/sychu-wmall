package com.wardrobe.interceptor;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by wangjun on 16/10/13.
 */
public class ResourceVersionFilter implements Filter {
    private static final long version = System.currentTimeMillis();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setAttribute("static_resource_version", version);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
