package org.chihx.seckill.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.HashMap;
import java.util.Map;

@Service
public class RedisPoolFactory {

    @Autowired
    RedisConfig redisConfig;

    @Bean
    public JedisPool JedisPoolFactory() {
        Map<String, String> map = new HashMap<String, String>();
        StringBuffer sb;
        try {
            AsynchronousServerSocketChannel aio = AsynchronousServerSocketChannel.open();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        // 秒转毫秒
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);


        JedisPool jp = new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getTimeout() * 1000, redisConfig.getPassword(), 0);
        return jp;
    }
}
