package com.tothefor.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

/**
 * @Author DragonOne
 * @Date 2022/3/18 10:56
 * @墨水记忆 www.tothefor.com
 */
@Component
public class emailSend {

    @Autowired
    private JavaMailSenderImpl javaMailSender; //mail自带

    @Autowired
    private TemplateEngine templateEngine; //thymeleaf自带

    public void sendEmail(String xh,String code,String inMan) throws Exception{
        //创建
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //组装
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

        //邮件标题
        mimeMessageHelper.setSubject("成理工程ACM集训队-邮件通知");
        //接受者
        mimeMessageHelper.setTo(inMan);
        //发送者
        mimeMessageHelper.setFrom("2629828909@qq.com");

        Context context = new Context();
        context.setVariable("code",code); //在邮件模板里需要的值
        context.setVariable("xh",xh); //在邮件模板里需要的值

        String process = templateEngine.process("codeEmail", context); //HTML邮件模板的名称
        mimeMessageHelper.setText(process,true);

        javaMailSender.send(mimeMessage);

    }
}
