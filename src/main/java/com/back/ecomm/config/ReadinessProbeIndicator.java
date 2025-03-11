package com.back.ecomm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class ReadinessProbeIndicator implements HealthIndicator {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Health health() {
        try {
            // Check SMTP server connectivity
            boolean smtpAvailable = checkSmtpServer();
            
            return Health.up()
                    .withDetail("database", "connected")
                    .withDetail("smtpServer", smtpAvailable ? "available" : "unavailable")
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withException(e)
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }

    private boolean checkSmtpServer() {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.timeout", "5000");

            // Create a test message
            MimeMessage message = mailSender.createMimeMessage();
            message.setFrom(new InternetAddress("mageek@gmail.com"));
            message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse("mageek@gmail.com"));
            message.setSubject("Health Check");
            message.setText("This is a health check message.");

            // Try to send the message
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
} 