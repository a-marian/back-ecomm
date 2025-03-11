package com.back.ecomm.service;

import com.back.ecomm.entity.Role;
import com.back.ecomm.entity.User;
import com.back.ecomm.entity.VerificationToken;
import com.back.ecomm.record.UserRecord;
import com.back.ecomm.repository.UserRepository;
import com.back.ecomm.repository.VerificationTokenRepository;
import com.back.ecomm.util.CustomPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    private VerificationTokenRepository verificationTokenRepository;
    private CustomPasswordEncoder passwordEncoder;

    public UserServiceImpl(@Autowired UserRepository userRepository,
                           @Autowired VerificationTokenRepository verificationTokenRepository,
                           @Autowired CustomPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.verificationTokenRepository= verificationTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(UserRecord userRecord) throws UsernameNotFoundException {
        User user = null;
        try{
            user= new User();
            user.setMail(userRecord.mail());
            user.setUsername(userRecord.username());
            user.setPassword(passwordEncoder.encode(userRecord.password()));
            userRepository.save(user);
        } catch (Exception ex){
            LOGGER.error(" error trying to save a new user");
        }
        return  user;
    }


    @Override
    public boolean existsUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
       return user.isPresent();
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username"+ username));

        return new org.springframework.security.core
                .userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
