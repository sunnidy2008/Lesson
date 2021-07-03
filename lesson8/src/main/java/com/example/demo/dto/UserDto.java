package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: DELL
 * @Date: 2021-01-16 11:00
 * @Description:
 */
@Data
public class UserDto {

    private String name;
    private Integer age;
    private Date birthday;
}
