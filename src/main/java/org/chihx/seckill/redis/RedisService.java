package org.chihx.seckill.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    /**
     * 获取单个对象
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realPrefix = prefix.getPrefix() + key;
            String str = jedis.get(realPrefix);
            T t = strToBean(str, clazz);
            return t;
        } finally {
            // 将连接返回给连接池
            returnToPool(jedis);
        }
    }

    /**
     *  设置对象
     * @param prefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String val = beanToStr(value);
            // 有效时间
            int seconds = prefix.expireSeconds();
            String realPrefix = prefix.getPrefix() + key;
            if (seconds <= 0) {
                jedis.set(realPrefix, val);
            } else {
                jedis.setex(realPrefix, seconds, val);
            }
            return true;
        } finally {
            // 将连接返回给连接池
            returnToPool(jedis);
        }
    }

    /**
     * 判断是否存在
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realPrefix = prefix.getPrefix() + key;
            Boolean exists = jedis.exists(realPrefix);
            return exists;
        } finally {
            // 将连接返回给连接池
            returnToPool(jedis);
        }
    }

    /**
     * 增加值
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realPrefix = prefix.getPrefix() + key;
            Long incr = jedis.incr(key);
            return incr;
        } finally {
            // 将连接返回给连接池
            returnToPool(jedis);
        }
    }

    /**
     * 减少值
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realPrefix = prefix.getPrefix() + key;
            Long decr = jedis.decr(key);
            return decr;
        } finally {
            // 将连接返回给连接池
            returnToPool(jedis);
        }
    }

    private <T> String beanToStr(T value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Integer) {
            return value.toString();
        } else if (value instanceof String) {
            return value.toString();
        } else if (value instanceof Long) {
            return value.toString();
        } else {
            return JSON.toJSONString(value);
        }
    }

    private <T> T strToBean(String str, Class<T> clazz) {
        if (str == null) {
            return null;
        }
        if (clazz == Integer.class && clazz == int.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == Long.class && clazz == long.class) {
            return (T) Long.valueOf(str);
        } else {
            return (T) JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


}
