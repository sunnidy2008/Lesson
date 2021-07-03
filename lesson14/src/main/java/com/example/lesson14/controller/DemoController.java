package com.example.lesson14.controller;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import com.example.lesson14.dto.UserDto;
import org.redisson.api.*;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: DELL
 * @Date: 2021-02-08 09:12
 * @Description:
 */
@RestController
@RequestMapping("demo")
public class DemoController {
    @Resource
    private RedissonClient redissonClient;

    public static String MAP_KEY = "MAP";
    public static String SET_KEY = "SET";
    public static String LIST_KEY = "LIST";

    @GetMapping("test")
    public String test(){
        RMap<String, UserDto> map = redissonClient.getMap("MAP_KEY");
        UserDto userDto = new UserDto();
        userDto.setUsername("张三6d");
        userDto.setAge(18);
        userDto.setBirthday(new Date());
        userDto.setFav(Arrays.asList("apple","pear"));
        map.put("userDto",userDto);
        return "ok";
    }

    @GetMapping("put")
    public String put(){
        RMap<String, String> map = redissonClient.getMap(MAP_KEY, JsonJacksonCodec.INSTANCE);
        map.put("a","hello redisson");
        return "ok";
    }

    @GetMapping("get")
    public String get(){
        RMap<String, String> map = redissonClient.getMap(MAP_KEY);
        return map.get("a");
    }

    @GetMapping("put2")
    public String put2(){
        RMap<String, UserDto> map = redissonClient.getMap(MAP_KEY, JsonJacksonCodec.INSTANCE);
        UserDto userDto = new UserDto();
        userDto.setUsername("张三6");
        userDto.setAge(18);
        userDto.setBirthday(new Date());
        userDto.setFav(Arrays.asList("apple","pear"));
        map.put("userDto",userDto);
        return "ok";
    }

    @GetMapping("get2")
    public UserDto get2() throws InterruptedException {
        RMap<String, UserDto> map = redissonClient.getMap(MAP_KEY, JsonJacksonCodec.INSTANCE);
        UserDto userDto = map.get("userDto");
        return userDto;
    }


    @GetMapping("put3")
    public String put3(){
        RSet<Long> set = redissonClient.getSet(SET_KEY, JsonJacksonCodec.INSTANCE);
        set.add(System.currentTimeMillis());
        return "ok";
    }

    @GetMapping("get3")
    public String get3() throws InterruptedException {
        RSet<Long> set = redissonClient.getSet(SET_KEY, JsonJacksonCodec.INSTANCE);
        Iterator<Long> iterator = set.iterator();
        String result = "";
        while (iterator.hasNext()){
            result +=","+iterator.next();
        }
        return result;
    }

    @GetMapping("put4")
    public String put4(){
        RList<Long> list = redissonClient.getList(LIST_KEY, JsonJacksonCodec.INSTANCE);
        list.add(1L);
        return "ok";
    }

    @GetMapping("get4")
    public String get4() throws InterruptedException {
        RList<Long> list = redissonClient.getList(LIST_KEY, JsonJacksonCodec.INSTANCE);
        String result="";
        for (Long aLong : list) {
            result+=aLong;
        }
        return result;
    }
}
