package com.jiangkedev.redis;

import com.jiangkedev.lock.RedisLock;
import org.springframework.stereotype.Component;

/**
 * author:bazz jiang
 * date:Create in 2018-08-22
 * email:bazzjiang@gmail.com
 */
@Component
public class LockTest {
    @RedisLock(value = "jiangke",keepMills = 300000)
    public void addddLock(){
        System.out.println("hello world!");
    }
}
