package com.example.lesson16.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: DELL
 * @Date: 2021-07-03 09:38
 * @Description:
 */
@RestController
@Api(tags = "用户相关api")
public class UserController2 {

    @GetMapping("test")
    public void test(){

    }
}
