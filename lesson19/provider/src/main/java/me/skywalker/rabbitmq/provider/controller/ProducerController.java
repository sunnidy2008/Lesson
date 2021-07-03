package me.skywalker.rabbitmq.provider.controller;

import me.skywalker.rabbitmq.provider.config.DirectRabbitConfig;
import me.skywalker.rabbitmq.provider.config.FanoutRabbitConfig;
import me.skywalker.rabbitmq.provider.config.TopicRabbitConfig;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
 
@RestController
public class ProducerController {
 
    @Autowired
    private RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法
 
    @GetMapping("/sendDirectMsg")
    public String sendDirectMsg() {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",UUID.randomUUID().toString());
        map.put("data","hello,i am direct msg!");
        map.put("datetime",System.currentTimeMillis());
        //交换机 路由 消息（发送消息的时候不需要管队列，因为队列已经在DirectRabbitConfig中配置了，队列应该是消费者关心的事情）
        rabbitTemplate.convertAndSend(DirectRabbitConfig.directExchange, DirectRabbitConfig.directRouting, map);

        //模拟exchange不存在，routing存在
//        rabbitTemplate.convertAndSend("DirectRabbitConfig.directExchange", DirectRabbitConfig.directRouting, map);

        //模拟exchange存在，routing不存在
//        rabbitTemplate.convertAndSend(DirectRabbitConfig.directExchange, "DirectRabbitConfig.directRouting", map);

        //模拟exchange和routing都不存在
//        rabbitTemplate.convertAndSend("DirectRabbitConfig.directExchange", "DirectRabbitConfig.directRouting", map);


        return "ok";
    }


    @GetMapping("/sendFanoutMsg")
    public String sendFanoutMsg() {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("id",UUID.randomUUID().toString());
        map.put("data","hello,i am fanout msg!");
        map.put("datetime",System.currentTimeMillis());
        rabbitTemplate.convertAndSend(FanoutRabbitConfig.fanoutExchange, null, map);//扇型不需要路由key，设置了也无效
        return "ok";
    }

    @GetMapping("/sendTopicMsg1")
    public String sendTopicMsg1() {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("id",UUID.randomUUID().toString());
        map.put("data","hello,i am topic msg from apple!");
        map.put("datetime",System.currentTimeMillis());
        rabbitTemplate.convertAndSend(TopicRabbitConfig.topicExchange, TopicRabbitConfig.topicRoutingApple, map);
        return "ok";
    }

    @GetMapping("/sendTopicMsg2")
    public String sendTopicMsg2() {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("id",UUID.randomUUID().toString());
        map.put("data","hello,i am topic msg from fruit!");
        map.put("datetime",System.currentTimeMillis());
        rabbitTemplate.convertAndSend(TopicRabbitConfig.topicExchange, "fruit.orange", map);
        return "ok";
    }

    //延迟队列
    @GetMapping("/sendDelayMsg")
    public String sendDelayMsg() {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",UUID.randomUUID().toString());
        map.put("data","hello,i am sendDelayMsg msg!");
        map.put("datetime",System.currentTimeMillis());
        //交换机 路由 消息（发送消息的时候不需要管队列，因为队列已经在DirectRabbitConfig中配置了，队列应该是消费者关心的事情）
        rabbitTemplate.convertAndSend("delay_exchange11", "abc", map,message -> {
            //配置消息的过期时间
            message.getMessageProperties().setDelay(5000);
            return message;
        });

        return "ok";
    }
}
 
