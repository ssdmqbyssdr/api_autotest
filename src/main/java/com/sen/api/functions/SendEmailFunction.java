package com.sen.api.functions;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class SendEmailFunction {
    //邮件服务器主机名
    //企业邮箱 SMTP 服务器地址：
    private static String myEmailSMTPHost = "hwsmtp.qiye.163.com";

    //发件人邮箱
    private static String myEmailAccount = "bin.yu@ucloud.cn";

    //发件人邮箱授权码
    private static String myEmailPassword = "YUBINucloud1357.";


    public static void  sendEmail (String toEmailAddress,String emailTitle,String emailContent) throws Exception {

        Properties props = new Properties();

        //开启debug模式
        props.setProperty("mail.debug","true");

        //发送服务器不需要身份验证
        props.setProperty("mail.stmp.auth","false");

        //端口号
        props.put("mail.stmp.port","994");

        //设置邮件服务器主机名
        props.setProperty("mail.smtp.host", myEmailSMTPHost);

        //发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        //ssl认证，注意腾讯邮箱是基于ssl加密的，所以需要开启才可用
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);

        //设置是否使用ssl安全连接（一般都使用）
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        //创建会话
        Session session = Session.getInstance(props);


        //获取邮件对象
        //发送的消息，基于观察者模式设计的
        Message msg = new MimeMessage(session);

        //设置邮件标题
        msg.setSubject(emailTitle);

        //设置邮件内容
        //使用StringBuilder，因为StringBudilder加载速度会比String快，而且线程安全性也不错
        StringBuilder builder = new StringBuilder();

        //写入内容
        builder.append("\n"+emailContent);

        //设置显示发送时间
        msg.setSentDate(new Date());

        //设置邮件内容
        msg.setText(builder.toString());

        //设置发件人邮箱
        msg.setFrom(new InternetAddress(myEmailAccount,"我的邮箱","UTF-8"));

        //得到邮差对象
        Transport transport =session.getTransport();

        //连接自己的邮箱账户
        transport.connect(myEmailSMTPHost,myEmailAccount,myEmailPassword);

        //发送邮件
        transport.sendMessage(msg,new Address[]{new InternetAddress(toEmailAddress)});

        //将邮件保存到本地
        OutputStream out = new FileOutputStream("MyEmail.eml");
        msg.writeTo(out);
        out.flush();
        out.close();

        transport.close();

    }

}