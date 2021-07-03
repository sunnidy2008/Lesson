package com.example.demo.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: DELL
 * @Date: 2021-01-01 15:24
 * @Description:
 */

@Component
@ConfigurationProperties(prefix = "user")
public class User {

    private String name;
    private Integer age;
    private Date birthday;
    private Map fruit;
    private Map fruit1;
    private List animals;

    public List getAnimals() {
        return animals;
    }

    public void setAnimals(List animals) {
        this.animals = animals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Map getFruit() {
        return fruit;
    }

    public void setFruit(Map fruit) {
        this.fruit = fruit;
    }

    public Map getFruit1() {
        return fruit1;
    }

    public void setFruit1(Map fruit1) {
        this.fruit1 = fruit1;
    }
}
