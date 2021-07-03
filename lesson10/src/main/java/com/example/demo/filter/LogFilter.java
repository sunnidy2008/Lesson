package com.example.demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @Auther: DELL
 * @Date: 2021-01-17 11:52
 * @Description:
 */
@WebFilter(urlPatterns = "/*", filterName = "LogFilter")
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("logfilter.init is called...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter中打印的请求参数：");
        Enumeration<String> parameterNames = servletRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = servletRequest.getParameter(key);
            System.out.println(key+"==>"+value);
        }

        long bgn = System.currentTimeMillis();
        //一定要调用链式调用传递，否则也进不了controller
        filterChain.doFilter(servletRequest,servletResponse);
        long end = System.currentTimeMillis();
        System.out.println("filter中记录耗时："+(end-bgn)+"ms");
    }

    @Override
    public void destroy() {
        System.out.println("logfilter.destroy is called...");
    }
}
