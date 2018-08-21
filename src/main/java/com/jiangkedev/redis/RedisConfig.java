package com.jiangkedev.redis;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * author:bazz jiang
 * date:Create in 2018-06-19
 * email:bazzjiang@gmail.com
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{
    @Bean
    public RedisTemplate redisTemplate(LettuceConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //使用fast json
        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);
        return redisTemplate;
    }
    @Bean("keyGenerator")
    public KeyGenerator keyGenerator(){
        return (target, method, params) -> {
            String s = "";
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for(Object obj:params){
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }
    //缓存管理器
    @Bean
    public RedisCacheManager cacheManager(LettuceConnectionFactory redisConnectionFactory) {
        return RedisCacheManager.create(redisConnectionFactory);
    }
}
