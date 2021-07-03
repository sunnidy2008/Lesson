package com.example.lesson14.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Auther: DELL
 * @Date: 2021-02-08 09:33
 * @Description:
 */
@Data
public class UserDto implements Serializable {
    private String username;
    private Integer age;
    private Date birthday;
    private List<String> fav;
}
