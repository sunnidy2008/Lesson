package com.example.lesson11.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: DELL
 * @Date: 2021-01-18 17:09
 * @Description:
 */
@Service
public class Scheduling1Service {

//    //每2秒执行一次(若上次任务执行时间超过2秒，则立即执行，否则从上一个任务开始时算起2秒后执行本次任务)
//    @Scheduled(fixedRate = 2000)
//    public void test1() throws InterruptedException {
//        Thread.sleep(1000L);//模拟定时任务执行耗费了1s
//        Thread.sleep(3000L);//模拟定时任务执行耗费了3s
//        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(format.format(date)+"==>SchedulingService.test1 is called");
//    }
//
//    //上一个任务执行完2秒后，再执行本次任务
//    @Scheduled(fixedDelay = 2000)
//    public void test2() throws InterruptedException {
//        Thread.sleep(3000L);//模拟定时任务执行耗费了3s
//        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(format.format(date)+"==>SchedulingService.test2 is called");
//    }
//
//    //支持corn表达式
//    @Scheduled(cron = "0 0 1 * * ? ")
//    public void test3() throws InterruptedException {
//        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(format.format(date)+"==>SchedulingService.test3 is called");
//    }
}
