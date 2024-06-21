package com.sandbox.SpringApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpResponseDto {
    private UserDto user;
    private TokenDto tokens;
}
