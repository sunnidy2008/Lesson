package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan("com.example")
@SpringBootApplication
public class Lesson10Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson10Application.class, args);
    }

}
