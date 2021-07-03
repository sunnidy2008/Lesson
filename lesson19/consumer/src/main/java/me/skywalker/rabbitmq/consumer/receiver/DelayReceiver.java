package me.skywalker.rabbitmq.consumer.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RabbitListener(queues = "delay_queue11")//监听的队列名称 delay_queue，不需要管路由和交换机，因为这些是生产者管理的事情。消费者只需要关心队列即可
public class DelayReceiver {
    @RabbitHandler
    public void handler(Map testMessage) {
        System.out.println("DelayReceiver消费者收到消息  : " + testMessage.toString());
        for (Object item : testMessage.keySet()) {
            log.info("item:{}-->value:{}",item,testMessage.get(item));
        }
        log.info("");
    }
}