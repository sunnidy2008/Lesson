package com.example.lesson20.annotation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

/**
 * @Auther: DELL
 * @Date: 2021-07-02 15:30
 * @Description:
 */
@Target({ElementType.METHOD, ElementType.TYPE})//注解作用的位置，ElementType.METHOD表示该注解仅能作用于方法上
@Retention(RetentionPolicy.RUNTIME)//注解的生命周期，表示注解会被保留到什么阶段，可以选择编译阶段SOURCE、类加载阶段CLASS，或运行阶段RUNTIME
@Documented//注解信息会被添加到Java文档中
public @interface Log2DB {
    String name() default "接口";
    boolean enabled() default true;//是否启用，默认是
}
