package com.example.lesson20.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: DELL
 * @Date: 2021-07-02 17:25
 * @Description:
 */
@Slf4j
@Component
public class ApisScaner {

    @Resource
    private ApplicationContextProvider applicationContextProvider;

    //key是全类名.方法名，value是ApiOperation的name属性
    public static Map<String,String> APIS = new HashMap<String,String>();

    @PostConstruct
    public void handler() throws ClassNotFoundException {
        log.info("SwaggerScaner.handler");
        ApplicationContext applicationContext = applicationContextProvider.getApplicationContext();
        //获取自定义注解的配置
        final Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Api.class);

        for (String key : beansWithAnnotation.keySet()) {

            Method[] methods = Class.forName(beansWithAnnotation.get(key).getClass().getName()).getMethods();
            for (Method method : methods) {

                ApiOperation annotation = AnnotationUtils.findAnnotation(method, ApiOperation.class);
                if(annotation!=null){
                    String value = annotation.value();
                    String className = beansWithAnnotation.get(key).toString();
                    String methodName = method.getName();
                    className = className.substring(0,className.indexOf("@"));

                    APIS.put(className+"."+methodName,value);
                }
            }
        }

        for (String item : APIS.keySet()) {
            log.info("item:{}-->value:{}",item, APIS.get(item));
        }
    }
}
