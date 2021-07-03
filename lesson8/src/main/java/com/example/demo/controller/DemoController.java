package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

/**
 * @Auther: DELL
 * @Date: 2021-01-16 10:59
 * @Description:
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("getUser")
    public void getUser(@RequestParam("name")String name) throws Exception {
        //此处需要远程调用第三方服务 获取user对象
        UserDto forObject = restTemplate.getForObject(new URI("http://localhost:8080/api/get?username=" + name), UserDto.class);
        System.out.println(new ObjectMapper().writeValueAsString(forObject));
    }

    @PostMapping("saveUser")
    public void getUser(){
        UserDto userDto = new UserDto();
        userDto.setName("resttemplate");
        userDto.setAge(17);
        userDto.setBirthday(new Date());

        //此处需要远程调用第三方服务 将user对象发送给第三方
        String result = restTemplate.postForObject("http://localhost:8080/api/save", userDto, String.class);
        System.out.println(result);
    }
}
