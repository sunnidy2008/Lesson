package me.skywalker.rabbitmq.consumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.Map;
@Component
@RabbitListener(queues = "fanoutQueue2")
public class FanoutReceiver2 {
 
    @RabbitHandler
    public void handler(Map testMessage) {
        System.out.println("FanoutReceiver2消费者收到消息  : " +testMessage.toString());
    }
 
}