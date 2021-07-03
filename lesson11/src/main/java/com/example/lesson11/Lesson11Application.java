package com.example.lesson11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Lesson11Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson11Application.class, args);
    }

}
