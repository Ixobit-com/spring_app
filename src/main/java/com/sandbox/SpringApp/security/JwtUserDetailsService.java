package com.sandbox.SpringApp.security;

import com.sandbox.SpringApp.entity.UserEntity;
import com.sandbox.SpringApp.security.jwt.JwtUserFactory;
import com.sandbox.SpringApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findByEmail(username);
        if (user == null) {
            throw new AccessDeniedException("Token's owner name not found in db");
        }

        return JwtUserFactory.create(user);
    }
}
