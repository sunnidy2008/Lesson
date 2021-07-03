package com.example.lesson14.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.redisson")//获取yml文件中以spring.redisson开头的所有数据
public class RedissonProperties {
    //映射yml文件里面的数据
    private String address;
    private String password = null;
    private int database = 0;
    private int timeout = 3000;
}
