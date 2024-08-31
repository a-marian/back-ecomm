package com.back.ecomm.controller;

import com.back.ecomm.record.UserRecord;
import com.back.ecomm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody UserRecord user){
        if(userService.existsUser(user.username(), user.email())){
            return ResponseEntity.ok("User already registered");
        }

        if (!userService.save(user)){
            return ResponseEntity.ok("user not registered, try again");
        }
        return ResponseEntity.ok("user registered");
    }
}
