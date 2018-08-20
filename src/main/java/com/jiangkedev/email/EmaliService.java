package com.jiangkedev.email;

/**
 * author:bazz jiang
 * date:Create in 2018-04-21
 * email:bazzjiang@gmail.com
 */
public interface EmaliService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttacment, String attachName);
    void sendTemplateMessage(String to, String subject);
}
