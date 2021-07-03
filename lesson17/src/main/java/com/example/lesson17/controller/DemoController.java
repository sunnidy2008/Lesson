package com.example.lesson17.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: DELL
 * @Date: 2021-01-23 14:09
 * @Description:
 */
@Slf4j
@RestController
public class DemoController {

    @GetMapping("test")
    public void test(){
//        while(true){
            log.trace("trace log");
            log.debug("debug log");
            log.info("info log");
            log.warn("warn log");
            log.error("error log");

            String param="springboot 课程";
            log.info("请求参数：{},结果：{}",param,"hello slf4j");
//        }
    }
}
