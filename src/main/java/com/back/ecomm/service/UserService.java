package com.back.ecomm.service;

import com.back.ecomm.entity.User;
import com.back.ecomm.entity.VerificationToken;
import com.back.ecomm.record.UserRecord;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService extends UserDetailsService {

    User save(UserRecord userRecord) throws UsernameNotFoundException;

    boolean existsUser(String username);

    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String verificationToken);
}
