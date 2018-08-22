package com.jiangkedev.lock;

import java.lang.annotation.*;

/**
 * @author jiangke
 * @date 21:58
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisLock {
    /**锁的资源,redis的key*/
    String value() default "default";
    /**锁的等待时间*/
    long keepMills() default 30000;
    /**当获取失败时候的动作*/
    LockFailAction action() default LockFailAction.CONTINUE;
    public enum LockFailAction{
        /**放弃*/
        GIVE_UP,
        /**继续*/
        CONTINUE
    }
    /**重试的间隔时间，设置GIVE_UP忽略此项*/
    long sllepMills() default 200;
    /**重试次数*/
    int retryTimes() default 5;
}
