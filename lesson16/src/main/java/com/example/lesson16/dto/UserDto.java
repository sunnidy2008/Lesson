package com.example.lesson16.dto;

import com.example.lesson16.enums.SexEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;


/**
 * @Auther: DELL
 * @Date: 2021-01-24 11:49
 * @Description:
 */
@ApiModel("用户对象")
@Data
public class UserDto {
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @Enumerated(EnumType.STRING)
    @ApiModelProperty("性别")
    private SexEnum sex;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("邮箱")
    private String email;
}
