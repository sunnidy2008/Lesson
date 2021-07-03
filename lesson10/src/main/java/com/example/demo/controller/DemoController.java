package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: DELL
 * @Date: 2021-01-17 11:40
 * @Description:
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("test1")
    public String test1(){
        System.out.println("rest.test1 is called");
        return "test1";
    }

    @GetMapping("test2")
    public String test2(){
        System.out.println("rest.test2 is called");
        return "test2";
    }

    @GetMapping("test3")
    public String test3(@RequestParam("a")String a){
        System.out.println("rest.test3 is called");
        return "test3";
    }
}
