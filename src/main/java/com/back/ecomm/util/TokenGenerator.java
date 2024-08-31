package com.back.ecomm.util;

import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Collections;

@Component
public class TokenGenerator {

    public static String generateToken() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //Build the JWT
        JwtClaimsSet jwtBuilder = JwtClaimsSet.builder()
                .issuer("http://example.com")
                .audience(Collections.singletonList("https://example.com/resource"))
                .id("1234")
                .subject("user@example.com")
                .expiresAt(Instant.from(Instant.now().plusSeconds(3600)))
                .issuedAt(Instant.now()).build();

        return "it needs to be completed";

    }
}
