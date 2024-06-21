package com.sandbox.SpringApp.service;

import com.sandbox.SpringApp.dto.LoginDto;
import com.sandbox.SpringApp.dto.TokenDto;
import com.sandbox.SpringApp.dto.UserSignUpRequestDto;
import com.sandbox.SpringApp.dto.UserSignUpResponseDto;

public interface AuthService {
    UserSignUpResponseDto register(UserSignUpRequestDto dto);

    TokenDto login(LoginDto dto);
}
