package com.example.demo.config;

import com.example.demo.filter.Log1Filter;
import com.example.demo.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean reqResFilter1() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        LogFilter logFilter = new LogFilter();
        filterRegistrationBean.setFilter(logFilter);
        filterRegistrationBean.addUrlPatterns("/demo/test1","/demo/test3");//配置过滤规则
        filterRegistrationBean.addInitParameter("name","spingboot");//设置init参数
        filterRegistrationBean.setName("logFilter");//设置过滤器名称
        filterRegistrationBean.setOrder(2);//执行次序

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean reqResFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        Log1Filter log1Filter = new Log1Filter();
        filterRegistrationBean.setFilter(log1Filter);
        filterRegistrationBean.addUrlPatterns("*");//配置过滤规则
        filterRegistrationBean.setName("Log1Filter");//设置过滤器名称
        filterRegistrationBean.setOrder(1);//执行次序

        return filterRegistrationBean;
    }
}