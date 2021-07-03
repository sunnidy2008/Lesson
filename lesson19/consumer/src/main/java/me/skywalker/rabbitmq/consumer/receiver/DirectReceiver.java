package me.skywalker.rabbitmq.consumer.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@RabbitListener(queues = "directQueue")//监听的队列名称 directQueue，不需要管路由和交换机，因为这些是生产者管理的事情。消费者只需要关心队列即可
public class DirectReceiver {
    @RabbitHandler
    public void handler(Map testMessage) {
        System.out.println("directReceiver消费者收到消息  : " + testMessage.toString());
        for (Object item : testMessage.keySet()) {
            log.info("item:{}-->value:{}",item,testMessage.get(item));
        }
        log.info("");
    }

//    @RabbitHandler
//    public void processHandler(Map testMessage,Message message,Channel channel) throws IOException {
//        try{
//            System.out.println("directReceiver消费者收到消息  : " + testMessage.toString());
//            log.info("channel：{}",channel);
//            log.info("message：{}",message);
//            int i=1/0;
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        }catch (Exception e){
//            //是否是重新投递的消息
//            if(message.getMessageProperties().getRedelivered()){
//                log.error("消息已重复处理，不再消费");
//                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
//            }else{
//                log.error("消息即将再次返回队列处理");
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
//            }
//        }
//    }
}