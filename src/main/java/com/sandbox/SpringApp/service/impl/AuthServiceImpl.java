package com.sandbox.SpringApp.service.impl;

import com.sandbox.SpringApp.dto.LoginDto;
import com.sandbox.SpringApp.dto.TokenDto;
import com.sandbox.SpringApp.dto.UserSignUpRequestDto;
import com.sandbox.SpringApp.dto.UserSignUpResponseDto;
import com.sandbox.SpringApp.entity.UserEntity;
import com.sandbox.SpringApp.security.jwt.JwtTokenProvider;
import com.sandbox.SpringApp.service.AuthService;
import com.sandbox.SpringApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;


    public AuthServiceImpl(JwtTokenProvider jwtTokenProvider,
                           AuthenticationManager authenticationManager,
                           UserService userService){
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public UserSignUpResponseDto register(UserSignUpRequestDto dto) {
        UserEntity registeredUser = userService.registerUser(dto);
        TokenDto tokens = jwtTokenProvider.createToken(registeredUser.getEmail(), registeredUser.getRoles());
        return new UserSignUpResponseDto(registeredUser.toDto(), tokens);
    }

    @Override
    public TokenDto login(LoginDto dto) {
        try{
            UserEntity user = userService.findByEmail(dto.getEmail());
            if(user == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
            return jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
        }catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username or password");
        }
    }
}
