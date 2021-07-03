package me.skywalker.rabbitmq.consumer.receiver;
 
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.Map;
 

@Component
@RabbitListener(queues = "topicQueue2")
public class Topic2Receiver {
 
    @RabbitHandler
    public void handler(Map testMessage) {
        System.out.println("Topic2Receiver消费者收到消息  : " + testMessage.toString());
    }
}