package com.example.lesson14.config;

import io.netty.channel.nio.NioEventLoopGroup;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * redisson 客户端配置
 */
@Configuration
@ConditionalOnClass(RedissonClient.class)//存在RedissonClient才创建该类
@ConditionalOnProperty({"spring.redisson.address"})//存在spring.redisson.address配置才创建该类
public class RedissonConfig {

    private static String codec = "org.redisson.codec.JsonJacksonCodec";

    @Resource
    private RedissonProperties properties;

    @Bean(destroyMethod = "shutdown")
    RedissonClient redissonClient(RedissonProperties properties) throws Exception {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig
                .setAddress(properties.getAddress())
                .setDatabase(properties.getDatabase())
                .setTimeout(properties.getTimeout());
        //密码不为空才设置，否则默认yml注入的空串会导致创建redisson不成功
        if(!StringUtils.isEmpty(properties.getPassword())){
            singleServerConfig.setPassword(properties.getPassword());
        }
        //指定默认序列化
        Codec codec=(Codec) ClassUtils.forName("org.redisson.codec.JsonJacksonCodec", ClassUtils.getDefaultClassLoader()).newInstance();
        config.setCodec(codec);
        return Redisson.create(config);
    }
}