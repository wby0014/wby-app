package com.example.wby.redis.util;

import com.example.wby.common.util.JsonUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@SuppressWarnings("unused")
@Component
public class StringRedisCacheUtils {


	@Resource
	private StringRedisTemplate stringRedisTemplate;

	public boolean exists(String key) {
		return stringRedisTemplate.hasKey(key);
	}

	public void set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	public void setExpire(String key,String value,long time){
		stringRedisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
	}

	public long getExpire(String key){
		return stringRedisTemplate.opsForValue().getOperations().getExpire(key, TimeUnit.SECONDS);
	}

	public void setEx(String key, Object value, int millSeconds) {
		String s = JsonUtil.toJson(value);
		stringRedisTemplate.opsForValue().set(key, s, millSeconds, TimeUnit.MILLISECONDS);
	}

	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}


	public void hmset(String key, Map<String, String> map) {
		stringRedisTemplate.opsForHash().putAll(key, map);
	}

	public long hdel(String key, String... keys) {
		return stringRedisTemplate.opsForHash().delete(key, keys);
	}

	public void put(String key, String hashKey, String value) {
		stringRedisTemplate.opsForHash().put(key, hashKey, value);
	}

	public List<Object> hget(String key) {
		return stringRedisTemplate.opsForHash().values(key);
	}

	public Object get(String key, String hashKey){
		return stringRedisTemplate.opsForHash().get(key, hashKey);
	}

	public long del(String key, String hashKey){
		return stringRedisTemplate.opsForHash().delete(key, hashKey);
	}

    public void del(String key){
        stringRedisTemplate.opsForValue().getOperations().delete(key);
    }

}
