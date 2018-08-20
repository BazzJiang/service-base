package com.jiangkedev.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * author:bazz jiang
 * date:Create in 2018-06-19
 * email:bazzjiang@gmail.com
 */
@Component
public class RedisCacheUtil {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    public ValueOperations<String,Object> setCacheObject(String key, Object value)
    {

        ValueOperations<String,Object> operation = redisTemplate.opsForValue();
        operation.set(key,value);
        return operation;
    }

}
