package com.example.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

/**
 * @Auther: DELL
 * @Date: 2021-01-17 11:29
 * @Description:
 */
@Component
public class LogInterceptor implements HandlerInterceptor {
    //在请求rest接口之前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("请求url："+request.getRequestURL());
        Map<String, String[]> parameterMap = request.getParameterMap();
        Enumeration<String> parameterNames = request.getParameterNames();

        System.out.println("请求参数：");
        while (parameterNames.hasMoreElements()) {
            String name = (String) parameterNames.nextElement();
            String value = request.getParameter(name);
            System.out.println(name+"==>"+value);
        }

        //假设参数a传递的如果是1的话，是不合法的请求，不继续往下执行
        if("1".equals(request.getParameter("a"))){
            return false;
        }
        return true;//如果返回false则不会继续往下执行，不会真正的进入到controller
    }

    //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle is called...");
    }

    //在整个请求结束之后被调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion is called...");
    }
}
