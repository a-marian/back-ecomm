package com.back.ecomm.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ConfigurationProperties
public class EmailSenderConfig {

    private static final int GMAIL_PORT = 587;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String user;
    @Value("${spring.mail.password}")
    private String password;


    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(GMAIL_PORT);
        //set up email config
        mailSender.setUsername(user);
        mailSender.setPassword(password);
        return mailSender;
    }

}
