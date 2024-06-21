package com.sandbox.SpringApp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSignUpRequestDto {
    private String email;
    private String password;
}
