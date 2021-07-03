package com.example.lesson20.controller;

import com.example.lesson20.annotation.Log2DB;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Auther: DELL
 * @Date: 2021-07-02 16:22
 * @Description:
 */
@Api("testController相关操作")
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Log2DB(name = "获取用户")
    @GetMapping("get")
    @ApiOperation("获取用户1")
    public String getUser(@RequestParam("userId")String userId){
        log.info("get is called");
        return "get is called";
    }

    @Log2DB(name = "获取用户")
    @PostMapping("post")
    @ApiOperation("保存用户1")
    public String postUser(@RequestBody Map request){
        log.info("post is called");
        return "post is called";
    }

}
