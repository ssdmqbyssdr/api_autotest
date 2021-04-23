package com.sen.api.functions;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import java.util.Properties;

public class SendEmailFunction {
    public static void main(String[] args){
        //收件人电子邮箱
        String to = "bin.yu@ucloud.cn";

        //发件人电子邮箱
        String from = "bin.yu@ucloud.cn";

        //指定发送邮件d的主机host
//        String host = "localhost";

        //获取系统属性
        Properties properties = System.getProperties();

        //设置邮件服务器
        properties.setProperty("mail.host","hwsmtp.qiye.163.com");

        properties.setProperty("mail.transport.protocol","smtp");

        //获取默认session对象
        Session session = Session.getDefaultInstance(properties);


        try{
            //创建默认的MimeMessage对象
            MimeMessage message = new MimeMessage(session);

            //Set From ：头部头字段
            message.setFrom(new InternetAddress(from));

            //Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

            // Set Subject: 头字段
            message.setSubject("测试报告");

            // 发送 HTML 消息, 可以插入html标签
            message.setContent("<h1>This is actual message</h1>",
                    "text/html" );

            // 设置消息体
            message.setText("xiaoxiti");

            Transport.send(message);
            System.out.println("Send message successfully");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
