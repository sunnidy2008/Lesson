package com.example.lesson13.controller;

import com.example.lesson13.exception.MyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: DELL
 * @Date: 2021-01-23 15:36
 * @Description:
 */
@RestController
public class DemoController {

    @GetMapping("test1")
    public String test1() throws Exception{
        if(true){
            throw new NullPointerException("NullPointerException");
        }
        return "ok";
    }

    @GetMapping("test2")
    public String test2() throws Exception{
        if(true){
            throw new RuntimeException("RuntimeException");
        }
        return "ok";
    }

    @GetMapping("test3")
    public String test3() throws MyException{
        if(true){
            //不能直接拋Exception 否则不能捕获，可以自己定义一个异常
            throw new MyException("MyException");
        }
        return "ok";
    }
}
