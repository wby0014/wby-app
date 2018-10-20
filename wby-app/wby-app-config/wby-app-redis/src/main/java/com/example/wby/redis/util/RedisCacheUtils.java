package com.example.wby.redis.util;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Component
public class RedisCacheUtils {


    @Resource
    private RedisTemplate redisTemplate;

    public void setEx(String key, Object value, int seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void incre(String key, long l) {
        redisTemplate.opsForValue().increment(key, l);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }

    public void del(String cacheName, String key) {
        this.del(generateKey(cacheName, key));
    }

    public void expire(String key, int seconds) {
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    public void hmset(String key, Map<? extends Object, ? extends Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public Map<Object, Object> hentries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public List<Object> hget(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    public Cursor<Map.Entry<Object, Object>> hscan(String key) {
        return redisTemplate.opsForHash().scan(key, ScanOptions.NONE);
    }

    public long hdel(String key, Object... keys) {
        return redisTemplate.opsForHash().delete(key, keys);
    }

    public void put(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public boolean hasKey(String key, Object hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    public void putIfAbsent(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    public <T> List<T> multiGet(String key, Collection<T> hashKeys) {
        return (List<T>) redisTemplate.opsForHash().multiGet(key,hashKeys);
    }



    /**
     * 生成redis所使用的key
     *
     * @param keys
     * @return
     */
    private String generateKey(Object... keys) {
        if (ObjectUtils.isEmpty(keys)) {
            return null;
        } else {
            StringBuilder keySB = new StringBuilder();
            for (Object key : keys) {
                keySB.append(key.toString()).append(':');
            }
            keySB.deleteCharAt(keySB.length() - 1);
            return keySB.toString();
        }
    }


}
