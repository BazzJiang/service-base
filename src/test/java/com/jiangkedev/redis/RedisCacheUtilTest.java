package com.jiangkedev.redis;

import com.jiangkedev.ApplicationBaseTest;
import com.jiangkedev.lock.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.testng.annotations.Test;

/**
 * author:bazz jiang
 * date:Create in 2018-06-19
 * email:bazzjiang@gmail.com
 */
public class RedisCacheUtilTest extends ApplicationBaseTest {
    @Autowired
    private DistributedLock distributedLock;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    @Test
    public void setCacheObjectTest(){
        if(!redisCacheUtil.sIsMember("test","111")){
            redisCacheUtil.sAdd("test","111222");
        }
    }
    @Test
    public void testDistributedGetLock(){
        boolean res = distributedLock.acquire("jiangkelovezll","zll",20000);
        System.out.println(res);
    }
    @Test
    public void testDistributedReleaseLock(){
        boolean res = distributedLock.release("jiangkelovezll","zll");
        System.out.println(res);
    }
}
