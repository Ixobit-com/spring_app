package com.sandbox.SpringApp.controller;

import com.sandbox.SpringApp.dto.LoginDto;
import com.sandbox.SpringApp.dto.TokenDto;
import com.sandbox.SpringApp.dto.UserSignUpRequestDto;
import com.sandbox.SpringApp.dto.UserSignUpResponseDto;
import com.sandbox.SpringApp.service.AuthService;
import com.sandbox.SpringApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public AuthController(UserService userService,
                          AuthService authService){
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/sign_up")
    public ResponseEntity<UserSignUpResponseDto> register(@RequestBody UserSignUpRequestDto userDto){
        UserSignUpResponseDto responseBody = authService.register(userDto);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto){
        TokenDto tokens = authService.login(loginDto);
        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }


    @GetMapping("/test")
    public ResponseEntity<String> testTokens(){
        String responseBody = "Access granted";
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
