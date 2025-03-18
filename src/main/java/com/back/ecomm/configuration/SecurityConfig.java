package com.back.ecomm.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsService userService;



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        final DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws  Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticationProvider())
                .build();
    }

    @Bean
    public CorsConfiguration corsConfiguration(){
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true); //allows taking authentication with credentials
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));  // providing the allowed origin details,
        corsConfig.setAllowedMethods(Collections.singletonList("*")); // allowing all HTTP methods
        corsConfig.setAllowedHeaders(Collections.singletonList("*")); // allowing all the request headers
        corsConfig.setMaxAge(Duration.ofMinutes(5L)); // setting the max time till which the allowed origin will not make a preflight request again to check if the CORS is allowed or not
        return  corsConfig;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws  Exception {
        httpSecurity.cors(corsCustom -> corsCustom.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                         return   corsConfiguration();
                    }
                })).authorizeHttpRequests( auth ->
                        auth.requestMatchers( "/api/login", "/api/register", "/api/products/**").permitAll()
                                .anyRequest().authenticated())
                .logout(logout -> logout.invalidateHttpSession(true)
                        .clearAuthentication(true).permitAll());
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }
}
