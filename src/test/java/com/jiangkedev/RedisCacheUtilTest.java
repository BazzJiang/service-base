package com.jiangkedev;

import com.jiangkedev.redis.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * author:bazz jiang
 * date:Create in 2018-06-19
 * email:bazzjiang@gmail.com
 */
public class RedisCacheUtilTest extends ApplicationBaseTest {
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    @Test
    public void setCacheObjectTest(){
        redisCacheUtil.setCacheObject("111","11111");
    }
}
