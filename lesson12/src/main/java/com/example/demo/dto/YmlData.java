package com.example.demo.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Auther: DELL
 * @Date: 2021-01-16 11:41
 * @Description:
 */
@Data
@Component
public class YmlData {

    @Value("${name}")
    private String name;
    @Value("${lesson}")
    private String lesson;
    @Value("${desc}")
    private String desc;
}
