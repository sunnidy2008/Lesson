package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Auther: DELL
 * @Date: 2021-01-01 14:09
 * @Description:
 */
@RequestMapping("demo")
@RestController
public class DemoController {

    @GetMapping("method1")
    public String method1(){
        return "hello springboot";
    }

    @GetMapping("method2")
    public String method2(@RequestParam("a")String a){
        return "hello springboot:"+a;
    }

    @GetMapping("method3")
    public String method3(@RequestParam("a")Integer a,@RequestParam("b")Integer b){
        return "hello springboot:"+(a+b);
    }

    @GetMapping("method4/{c}")
    public String method4(@PathVariable("c")String c){
        return "hello springboot:"+c;
    }

    @PostMapping("method5")
    public String method5(@RequestBody String a){
        return "hello springboot:"+a;
    }

    @PostMapping("method6")
    public void method6(@RequestBody Map map){
        for (Object o : map.keySet()) {
            System.out.println(o+"-->"+map.get(o));
        }
    }
}
