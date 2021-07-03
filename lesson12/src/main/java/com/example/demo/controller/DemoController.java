package com.example.demo.controller;

import com.example.demo.dto.YmlData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: DELL
 * @Date: 2021-01-16 11:40
 * @Description:
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @Resource
    private YmlData ymlData;

    @GetMapping("test")
    public void test() throws JsonProcessingException {
        System.out.println(ymlData.getDesc());
    }
}
