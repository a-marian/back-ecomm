package com.back.ecomm.service;

import com.back.ecomm.entity.User;
import com.back.ecomm.record.UserRecord;
import com.back.ecomm.repository.UserRepository;
import com.back.ecomm.util.CustomPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    private CustomPasswordEncoder passwordEncoder;

    public UserServiceImpl(@Autowired UserRepository userRepository,
                           @Autowired CustomPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean save(UserRecord userRecord) throws UsernameNotFoundException {
        try{
            User user = new User();
            user.setMail(userRecord.email());
            user.setUsername(userRecord.username());
            user.setPassword(passwordEncoder.encode(userRecord.password()));
            userRepository.save(user);
        } catch (Exception ex){
            LOGGER.error(" error trying to save a new user");
            return false;
        }
        return  true;
    }

    @Override
    public boolean existsUser(String username, String email) {
        Optional<User> user = userRepository.findByUsernameOrMail(username, email);
       return user.isPresent();
    }

    @Override
    public String generateToken(String username) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
