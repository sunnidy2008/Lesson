package com.example.lesson13.config;

import com.example.lesson13.dto.ResultDto;
import com.example.lesson13.exception.MyException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.jws.WebResult;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler
        @ResponseBody
        @ResponseStatus(HttpStatus.OK)
        public ResultDto globalException(HttpServletResponse response, Exception ex){
                log.info("ExceptionHandler...");
                log.info("错误代码："  + response.getStatus());
                ResultDto resultDto = new ResultDto();
                resultDto.setCode(0);
                resultDto.setMsg(ex.getMessage());
                resultDto.setData(null);
                return resultDto;
        }

        @ExceptionHandler(MyException.class)
        @ResponseBody
        @ResponseStatus(HttpStatus.OK)
        public ResultDto myException(HttpServletResponse response, MyException ex){
                log.info("MyExceptionHandler...");
                log.info("错误代码："  + response.getStatus());
                ResultDto resultDto = new ResultDto();
                resultDto.setCode(0);
                resultDto.setMsg(ex.getMessage());
                resultDto.setData(null);
                return resultDto;
        }
}