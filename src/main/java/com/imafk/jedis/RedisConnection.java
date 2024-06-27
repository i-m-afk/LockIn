package com.imafk.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPooled;

@Configuration
public class RedisConnection {
    private RedisConfig redisConfig;

    @Autowired
    public RedisConnection(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    @Bean
    public JedisPooled jedisPool() {
        return new JedisPooled(redisConfig.getHOSTNAME(), redisConfig.getPORT());
    }
}
