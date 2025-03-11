package com.back.ecomm.util;

import com.back.ecomm.entity.User;
import com.back.ecomm.record.OnRegisteredUserEvent;
import com.back.ecomm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.logging.Logger;

@Component
public class RegistrationListener implements ApplicationListener<OnRegisteredUserEvent> {

    private static final String CONFIRMATION_URL = "/registrationConfirm";

    private static final Logger LOGGER = Logger.getLogger(RegistrationListener.class.getName());

    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegisteredUserEvent event) {
        this.confirmRegistration(event);

    }

    private void confirmRegistration(OnRegisteredUserEvent event){
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipientAddress= user.getMail();
        String subject="Registration confirmation";
        String confirmationURL= event.getAppUrl()+"/registration?confirmToken="+token;
        LOGGER.info("Confirmation URL: " + confirmationURL);
        String message = messageSource.getMessage("regSuccess", null, event.getLocale());
        LOGGER.info("Sending email to: " + recipientAddress);
        LOGGER.info(message);
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message+"\r\n"+ "http://localhost:8080"+confirmationURL);
        mailSender.send(email);

    }
}
