package com.jiangkedev;

import com.jiangkedev.lock.DistributedLockAutoConfiguration;
import com.jiangkedev.redis.RedisConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * author:bazz jiang
 * date:Create in 2018-04-07
 * email:bazzjiang@gmail.com
 */
@SpringBootTest(classes = ApplicationBaseTest.Config.class)
public class ApplicationBaseTest extends AbstractTestNGSpringContextTests {
    @Configuration
    @EnableCaching
    @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
    @Import(value = {DistributedLockAutoConfiguration.class, RedisConfig.class})
    @ComponentScan(basePackages = "com.jiangkedev")
    static class Config {
    }
}
