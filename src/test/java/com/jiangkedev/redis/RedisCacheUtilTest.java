package com.jiangkedev.redis;

import com.jiangkedev.ApplicationBaseTest;
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
        if(!redisCacheUtil.sIsMember("test","111")){
            redisCacheUtil.sAdd("test","111");
        }
    }
}
