package com.jiangkedev.lock;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author jiangke
 * @date 22:10
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class DistributedLockAutoConfiguration {
    @Bean
    @ConditionalOnBean(StringRedisTemplate.class)
    public DistributedLock redisDistributedLock(StringRedisTemplate redisTemplate){
        return new RedisDistributedLock(redisTemplate);
    }
}
