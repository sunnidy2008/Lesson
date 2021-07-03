package me.skywalker.rabbitmq.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class FanoutRabbitConfig {
    public static String fanoutQueue1="fanoutQueue1";
    public static String fanoutQueue2="fanoutQueue2";
    public static String fanoutQueue3="fanoutQueue3";
    public static String fanoutExchange = "fanoutExchange";
 
    @Bean
    public Queue queue1() {
        return new Queue(FanoutRabbitConfig.fanoutQueue1);
    }
    @Bean
    public Queue queue2() {
        return new Queue(FanoutRabbitConfig.fanoutQueue2);
    }
    @Bean
    public Queue queue3() {
        return new Queue(FanoutRabbitConfig.fanoutQueue3);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FanoutRabbitConfig.fanoutExchange);
    }

    @Bean
    public Binding bindingExchange1() {
        //将队列fanoutQueue1 绑定到 fanoutExchange
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }
    @Bean
    Binding bindingExchange2() {
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }
    @Bean
    Binding bindingExchange3() {
        return BindingBuilder.bind(queue3()).to(fanoutExchange());
    }
}