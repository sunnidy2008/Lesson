package me.skywalker.rabbitmq.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
public class TopicRabbitConfig {
    //绑定键
    public static String topicQueue1 = "topicQueue1";
    public static String topicQueue2 = "topicQueue2";
    public static String topicExchange = "topicExchange";
    public static String topicRoutingApple = "fruit.apple";
//    *  表示1~n个字符 (必须出现的)
//    #  表示0~n个字符 (可能不出现)
//    若队列绑定为#，则无视消息路由，接收所有消息
//    当*和#都未出现时，就相当于直连direct
    public static String topicRoutingFruit = "fruit.#";

    @Bean
    public Queue topicQueue1() {
        return new Queue(TopicRabbitConfig.topicQueue1);
    }
    @Bean
    public Queue topicQueue2() {
        return new Queue(TopicRabbitConfig.topicQueue2);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TopicRabbitConfig.topicExchange);
    }
 
 
    //只有携带路由key 为fruit.apple,才会分发到该队列
    @Bean
    public Binding bindingExchangeMessage() {
        return BindingBuilder.bind(topicQueue1()).to(exchange()).with(TopicRabbitConfig.topicRoutingApple);
    }
    //只要是消息携带的路由键是以fruit.开头,都会分发到该队列
    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(topicQueue2()).to(exchange()).with(TopicRabbitConfig.topicRoutingFruit);
    }
}