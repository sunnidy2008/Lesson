package me.skywalker.rabbitmq.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
public class DirectRabbitConfig {
    public static String directRouting = "directRouting";
    public static String directQueue = "directQueue";
    public static String directExchange = "directExchange";
    @Bean
    public Queue DirectQueue() {
        return new Queue(DirectRabbitConfig.directQueue,true);  //true 是否持久
    }
    @Bean
    public DirectExchange DirectExchange() {
        return new DirectExchange(DirectRabbitConfig.directExchange);
    }
    @Bean
    Binding bindingDirect() {
//        BindingBuilder.bind(队列A).to(交换机B).with(路由) 将队列A绑定到交换机B，使用路由C传递消息
        return BindingBuilder.bind(DirectQueue()).to(DirectExchange()).with(directRouting);
    }
}