package com.imafk.jedis;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class RedisConfig {

    @Value("${jedis.redis.hostname}")
    private String HOSTNAME;
    @Value("${jedis.redis.port}")
    private Integer PORT;

    public String getHOSTNAME() {
        return HOSTNAME;
    }

    public Integer getPORT() {
        return PORT;
    }

    @PostConstruct
    public void printProperty() {
        System.out.println("Redis server: " + HOSTNAME + PORT);
    }
}
