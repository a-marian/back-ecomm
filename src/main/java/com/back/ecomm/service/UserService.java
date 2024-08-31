package com.back.ecomm.service;

import com.back.ecomm.record.UserRecord;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService extends UserDetailsService {

    boolean save(UserRecord userRecord) throws UsernameNotFoundException;

    boolean existsUser(String username, String email);

    String generateToken(String username);
}
