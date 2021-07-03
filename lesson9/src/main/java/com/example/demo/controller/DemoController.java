package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: DELL
 * @Date: 2021-01-17 11:04
 * @Description:
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @GetMapping("test1")
    public String test1(){
        return "hello junit test";
    }
}
