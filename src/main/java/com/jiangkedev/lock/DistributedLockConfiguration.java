package com.jiangkedev.lock;

import org.aspectj.lang.Aspects;
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
public class DistributedLockConfiguration {
    @Bean
    @ConditionalOnBean(StringRedisTemplate.class)
    public DistributedLock redisDistributedLock(StringRedisTemplate redisTemplate){
        return new RedisDistributedLock(redisTemplate);
    }
/*    @Bean
    public DistributedLockAspect distributedLockAspect(){
        DistributedLockAspect aspect = Aspects.aspectOf(DistributedLockAspect.class);
        return aspect;
    }*/
}
