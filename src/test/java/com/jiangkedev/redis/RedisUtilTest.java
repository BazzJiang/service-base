package com.jiangkedev.redis;

import com.jiangkedev.ApplicationBaseTest;
import com.jiangkedev.lock.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * author:bazz jiang
 * date:Create in 2018-06-19
 * email:bazzjiang@gmail.com
 */
public class RedisUtilTest extends ApplicationBaseTest {
    @Autowired
    private DistributedLock distributedLock;
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    @Autowired
    private LockTest lockTest;
    @Test
    public void setCacheObjectTest(){
        if(!redisCacheUtil.sIsMember("test","111")){
            redisCacheUtil.sAdd("test","111222");
        }
    }
    @Test
    public void testAddLock(){
        lockTest.addddLock();
    }
}
