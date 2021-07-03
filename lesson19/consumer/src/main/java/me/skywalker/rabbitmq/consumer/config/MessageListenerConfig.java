package me.skywalker.rabbitmq.consumer.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import me.skywalker.rabbitmq.consumer.receiver.FanoutReceiver1;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : JCccc
 * @CreateTime : 2019/9/4
 * @Description :
 **/
//@Configuration
public class MessageListenerConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ默认是自动确认，这里改为手动确认消息
        //设置一个队列
        container.setQueueNames("directQueue","fanoutQueue1","fanoutQueue2");

        container.setMessageListener(new ChannelAwareMessageListener() {

            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                long deliveryTag = message.getMessageProperties().getDeliveryTag();
                try {
                    System.out.println("message-->"+new ObjectMapper().writeValueAsString(message));
//                    System.out.println("channel-->"+new ObjectMapper().writeValueAsString(channel));
//                    //因为传递消息的时候用的map传递,所以将Map从Message内取出需要做些处理
                    String msg = message.toString();
                    System.out.println(msg);
                    byte[] body = message.getBody();
                    String[] msgArray = msg.split("'");//可以点进Message里面看源码,单引号直接的数据就是我们的map消息数据

                    Map<String, String> msgMap = mapStringToMap(msgArray[1].trim(),3);

//                    for (String key : msgMap.keySet()) {
//                        System.out.println(key+"-->"+msgMap.get(key));
//                    }
//
//                    if ("directQueue".equals(message.getMessageProperties().getConsumerQueue())){
//
//                    }
//
//                    if ("fanoutQueue1".equals(message.getMessageProperties().getConsumerQueue())){
//
//                    }
//
//                    if ("fanoutQueue2".equals(message.getMessageProperties().getConsumerQueue())){
//
//                    }

                    channel.basicAck(deliveryTag, true);
//			channel.basicReject(deliveryTag, true);//为true会重新放回队列
                } catch (Exception e) {
                    channel.basicReject(deliveryTag, false);
                    e.printStackTrace();
                }
            }
        });

        return container;

    }

    private Map<String, String> mapStringToMap(String str, int enNum) {
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(",",enNum);
        Map<String, String> map = new HashMap<String, String>();
        for (String string : strs) {
            String key = string.split("=")[0].trim();
            String value = string.split("=")[1];
            map.put(key, value);
        }
        return map;
    }
}