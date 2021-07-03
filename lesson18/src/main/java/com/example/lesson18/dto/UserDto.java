package com.example.lesson18.dto;

import lombok.Data;

import javax.validation.constraints.*;


/**
 * @Auther: DELL
 * @Date: 2021-01-24 11:49
 * @Description:
 */
@Data
public class UserDto {
    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    @Min(message = "密码不能小于6位",value = 6)
    @Max(message = "密码不能超过8位",value = 8)
    private String password;

    @NotNull(message = "年龄不能为空")
    @DecimalMin(message = "18岁以下禁止注册",value = "18")
    private Integer age;

    @NotNull(message = "email不能为空")
    private String email;
}
