package me.skywalker.rabbitmq.consumer.receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Component;
import java.util.Map;
 
@Component
@RabbitListener(queues = "fanoutQueue3")
public class FanoutReceiver3 {
 
    @RabbitHandler
    public void handler(Map testMessage) {
        System.out.println("FanoutReceiver3消费者收到消息  : " +testMessage.toString());
    }

}