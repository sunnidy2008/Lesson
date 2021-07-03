package com.example.demo.service;

import org.springframework.stereotype.Service;

/**
 * @Auther: DELL
 * @Date: 2021-01-17 11:21
 * @Description:
 */
@Service
public class DemoService {

    public String test1(String param1,Long param2){
        return param1+param2;
    }
}
