package com.example.lesson11.service;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

/**
 * @Auther: DELL
 * @Date: 2021-01-18 17:53
 * @Description:
 */
@Service
public class Scheduling3Service implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("cccccccc");
                    }
                },
                triggerContext -> {
                    return new CronTrigger("0/1 * * * * ? ").nextExecutionTime(triggerContext);
                }
        );
    }
}
