package com.example.lesson18.controller;

import com.example.lesson18.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Auther: DELL
 * @Date: 2021-01-24 11:15
 * @Description:
 */
@Slf4j
@RestController
public class DemoController {



    @GetMapping("test1")
    public String test1(@RequestParam(value = "username")String username,
                        @RequestParam(value = "password",required = false,defaultValue = "18")Integer age) {
//        if(username==null){
//            throw new MyException("用户名不能为空");
//        }else if(username.length()<6){
//            throw new MyException("用户名长度不能小于6位");
//        }
        //TODO: 实际业务处理
        log.info("进行业务处理...{}{}",username,age);
        return "OK";
    }

    @PostMapping("test2")
    public String test2( @RequestBody @Validated UserDto dto){
        return "ok";
    }
}
