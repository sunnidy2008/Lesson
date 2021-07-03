package me.skywalker.rabbitmq.provider.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
 
@Configuration
public class RabbitCallbackConfig {
 
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(false);//延迟队列需要将其设置为false，否则将报router路由找不到。可以考虑设置两个rabbitTemplate。

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("confirm");
                System.out.println("correlationData："+correlationData);
                System.out.println("ack："+ack);
                System.out.println("cause："+cause);
                System.out.println();
            }
        });

        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                System.out.println("returnedMessage");
                System.out.println("returnedMessage.getMessage()-->"+returnedMessage.getMessage());
                System.out.println("returnedMessage.getReplyCode()-->"+returnedMessage.getReplyCode());
                System.out.println("returnedMessage.getReplyText()-->"+returnedMessage.getReplyText());
                System.out.println("returnedMessage.getExchange()-->"+returnedMessage.getExchange());
                System.out.println("returnedMessage.getRoutingKey()-->"+returnedMessage.getRoutingKey());
                System.out.println();
            }
        });
 
        return rabbitTemplate;
    }
 
}