/**
 * 
 */
package com.bird.business.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.List;

/**
 * @author cuihui
 *
 */
public class MailUtil {



    private JavaMailSenderImpl mailSender;
    
    /**
    *   JavaMailSenderImpl支持MimeMessages和SimpleMailMessages。
    * MimeMessages为复杂邮件模板，支持文本、附件、html、图片等。
    * SimpleMailMessages实现了MimeMessageHelper，为普通邮件模板，支持文本
    */
    private SimpleMailMessage simpleMailMessage;
    

    /**
     * 描述：Spring 依赖注入
     * @author cuihui
     * @date 
     * @version 1.0
     * @param mailSender
     * @since 1.8
     *
     */
    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }
    
    /**
     * 描述：Spring 依赖注入
     * @author cuihui
     * @date 
     * @version 1.0
     * @param simpleMailMessage void
     * @since 1.8
     *
     */
    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }
    
    /**
     * 单发
     *
     * @param recipient 收件人
     * @param subject 主题
     * @param content 内容
     */
    public void send(String recipient,String subject,String content){
    	System.out.println(simpleMailMessage);
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        mailSender.send(simpleMailMessage);
    }
    
    /**
     * 群发
     *
     * @param recipients 收件人
     * @param subject 主题
     * @param content 内容
     */
    public void send(List<String> recipients,String subject,String content){
        simpleMailMessage.setTo(recipients.toArray(new String[recipients.size()]));
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        mailSender.send(simpleMailMessage);
    }
}

