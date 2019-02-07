package com.jiangkedev;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * author:bazz jiang
 * date:Create in 2018-04-07
 * email:bazzjiang@gmail.com
 */
@SpringBootTest(classes = ApplicationBaseTest.Config.class)
public class ApplicationBaseTest extends AbstractTestNGSpringContextTests {
    @EnableCaching
    @EnableAspectJAutoProxy
    @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
    static class Config {
    }
}
