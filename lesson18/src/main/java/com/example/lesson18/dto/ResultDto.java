package com.example.lesson18.dto;

import lombok.Data;

/**
 * @Auther: DELL
 * @Date: 2021-01-23 15:43
 * @Description:
 */
@Data
public class ResultDto {
    //请求结果0表示失败，其他是成功
    private int code;
    //失败的消息
    private String msg;
    //实际返回到前端的数据
    private Object data;
}
