package com.jiangkedev.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * author:bazz jiang
 * date:Create in 2018-07-27
 * email:bazzjiang@gmail.com
 */
@Configuration
public class EmailConfig {
    @Bean
    public JavaMailSender sohuMailSender(){
        //需要去搜狐邮箱开启服务
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.sohu.com");
        mailSender.setPort(25);
        mailSender.setUsername("zhuce_@sohu.com");
        mailSender.setPassword("<ZaiDuSCU2012>");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }
}
