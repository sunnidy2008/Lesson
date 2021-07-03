package com.example.demo.controller;

import com.example.demo.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: DELL
 * @Date: 2021-01-01 15:06
 * @Description:
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @Value("${name}")
    private String name;
    @Value("${age}")
    private Integer age;
    @Value("${birthday}")
    private Date birthday;
    @Resource
    private User user;
    @Value("${animals}")
    private List<String> animals;

    @GetMapping("method")
    public void method(){
        System.out.println("name=>"+name);
        System.out.println("age=>"+age);
        System.out.println("birthday=>"+birthday);

        System.out.println(user.getName());
        System.out.println(user.getAge());
        System.out.println(user.getBirthday());

        System.out.println("遍历animals：");
        for (Object item : animals) {
            System.out.println(item);
        }
        System.out.println("遍历user对象中的animals：");
        for (Object animal : this.user.getAnimals()) {
            System.out.println(animal);
        }

        System.out.println("遍历user对象中的fruit：");
        for (Object item : this.user.getFruit().keySet()) {
            System.out.println(item+"->"+this.user.getFruit().get(item));
        }

        System.out.println("遍历user对象中的fruit1：");
        for (Object item : this.user.getFruit1().keySet()) {
            System.out.println(item+"->"+this.user.getFruit1().get(item));
        }
    }
}
