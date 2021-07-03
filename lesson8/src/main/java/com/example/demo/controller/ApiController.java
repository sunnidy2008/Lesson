package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Auther: DELL
 * @Date: 2021-01-16 10:59
 * @Description:
 */
@RestController
@RequestMapping("api")
public class ApiController {

    //模拟获取数据
    @GetMapping("get")
    public UserDto get(@RequestParam("username")String username){
        System.out.println("从数据库中查询username【"+username+"】的数据");
        UserDto userDto = new UserDto();
        userDto.setName("springboot");
        userDto.setAge(18);
        userDto.setBirthday(new Date());

        return userDto;
    }

    //模拟接收数据保存到数据库
    @PostMapping("save")
    public String save(@RequestBody UserDto userDto) throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(userDto));
        //TODO:保存到数据库
        return "success";
    }
}
