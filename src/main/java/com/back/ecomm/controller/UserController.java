package com.back.ecomm.controller;

import com.back.ecomm.entity.User;
import com.back.ecomm.record.OnRegisteredUserEvent;
import com.back.ecomm.record.UserRecord;
import com.back.ecomm.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody UserRecord userRecord, HttpServletRequest request){
        if(userService.existsUser(userRecord.username())){
            return ResponseEntity.ok("User already registered");
        }
        User user = userService.save(userRecord);
        
        if (user != null){
            String appURL = request.getContextPath();
            eventPublisher.publishEvent(new OnRegisteredUserEvent(user, Locale.getDefault(), appURL));
           
        }else{ 
            return ResponseEntity.ok("user not registered, try again");
        }
        return ResponseEntity.ok("user registered");
    }
}
