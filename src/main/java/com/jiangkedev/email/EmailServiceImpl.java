package com.jiangkedev.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * author:bazz jiang
 * date:Create in 2018-04-21
 * email:bazzjiang@gmail.com
 */
@Service
public class EmailServiceImpl implements EmaliService{
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("zhuce_@sohu.com");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailSender.send(mailMessage);
    }
    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttacment,String attachName) {
        try{
            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage,true);
            mimeMessageHelper.setFrom("zhuce_@sohu.com");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text);
            FileSystemResource resource = new FileSystemResource(new File(pathToAttacment));
            mimeMessageHelper.addAttachment(attachName,resource);
            mailSender.send(mailMessage);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sendTemplateMessage(String to, String subject) {
        try{
            final Context ctx = new Context();
            ctx.setVariable("name","jiangke");
            String htmlContent = templateEngine.process("mail-template",ctx);
            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage,true,"UTF-8");
            mimeMessageHelper.setFrom("zhuce_@sohu.com");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);


            mimeMessageHelper.setText(htmlContent,true);
            mailSender.send(mailMessage);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
