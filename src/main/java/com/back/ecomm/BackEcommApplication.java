package com.back.ecomm;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class BackEcommApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEcommApplication.class, args);
    }

}
