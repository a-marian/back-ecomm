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

@Component
public class RegistrationListener implements ApplicationListener<OnRegisteredUserEvent> {

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
        String message = messageSource.getMessage("message.regSucc", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message+"\r\n"+ "http://localhost:8080"+confirmationURL);
        mailSender.send(email);

    }
}
