package com.example.lesson16.controller;

import com.example.lesson16.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @Auther: DELL
 * @Date: 2021-01-24 11:15
 * @Description:
 */
@Api(tags = "用户相关api")
@Slf4j
@RestController
public class UserController {
    @ApiOperation("根据用户id获取详细")
    @GetMapping("detail")
    public String detail(@RequestParam(value = "userId")@ApiParam("用户id") String userId) {
        //TODO: 实际业务处理
        return "OK";
    }

    @ApiOperation("保存新用户")
    @PostMapping("save")
    public UserDto save( @RequestBody UserDto dto){
        return new UserDto();
    }
}
