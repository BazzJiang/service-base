package com.jiangkedev.lock;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiangke
 * @date 22:06
 */
@Aspect
@Configuration
@ConditionalOnClass(RedisDistributedLock.class)
@AutoConfigureAfter()
public class DistributedLockAutoAspectConfiguration {
}
