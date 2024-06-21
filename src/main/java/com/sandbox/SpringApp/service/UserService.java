package com.sandbox.SpringApp.service;

import com.sandbox.SpringApp.dto.UserSignUpRequestDto;
import com.sandbox.SpringApp.entity.UserEntity;

public interface UserService {
    UserEntity findByEmail(String email);
    UserEntity registerUser(UserSignUpRequestDto dto);
}
