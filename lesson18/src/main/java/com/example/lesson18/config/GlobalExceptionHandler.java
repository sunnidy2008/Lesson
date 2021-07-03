package com.example.lesson18.config;

import com.example.lesson18.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import javax.validation.UnexpectedTypeException;
import java.net.BindException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


        //请求体校验不通过
        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseBody
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResultDto methodArgumentNotValidException(HttpServletResponse response, MethodArgumentNotValidException ex){
                log.info("MethodArgumentNotValidException...");
                ResultDto resultDto = new ResultDto();
                resultDto.setCode(0);
                String errors = ex.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
                resultDto.setMsg(errors);
                resultDto.setData(null);
                return resultDto;
        }

        //缺少请求参数
        @ExceptionHandler(MissingServletRequestParameterException.class)
        @ResponseBody
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResultDto missingServletRequestParameterException(HttpServletResponse response, MissingServletRequestParameterException ex){
                log.info("missingServletRequestParameterException...");
                ResultDto resultDto = new ResultDto();
                resultDto.setCode(0);
                String errors = "请求参数 " + ex.getParameterName() + " 不存在";
                resultDto.setMsg(errors);
                resultDto.setData(null);
                return resultDto;
        }

        //
        @ExceptionHandler(UnexpectedTypeException.class)
        @ResponseBody
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResultDto UnexpectedTypeException(HttpServletResponse response, UnexpectedTypeException ex){
                log.info("missingServletRequestParameterException...");
                ResultDto resultDto = new ResultDto();
                resultDto.setCode(0);
                String errors = "请求参数 " + ex.getMessage() + " 不能为空";
                resultDto.setMsg(errors);
                resultDto.setData(null);
                return resultDto;
        }

        //缺少请求体
        @ExceptionHandler(HttpMessageNotReadableException.class)
        @ResponseBody
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResultDto missingServletRequestParameterException(HttpServletResponse response, HttpMessageNotReadableException ex){
                log.info("HttpMessageNotReadableException...");
                ResultDto resultDto = new ResultDto();
                resultDto.setCode(0);
                String errors = "缺少body请求体";
                resultDto.setMsg(errors);
                resultDto.setData(null);
                return resultDto;
        }

}