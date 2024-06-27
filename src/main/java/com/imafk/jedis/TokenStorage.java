package com.imafk.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPooled;

@Component
public class TokenStorage {

    private final JedisPooled jedisPooled;

    @Autowired
    public TokenStorage(RedisConnection redisConnection) {
        this.jedisPooled = redisConnection.jedisPool();
    }

    // method to store token in Redis
    public void storeToken(String key, String token) {
        jedisPooled.set(key, token);
    }

    // method to retrieve token from Redis
    public String retrieveToken(String key) {
        return jedisPooled.get(key);
    }
}
