package com.sandbox.SpringApp.service.impl;

import com.sandbox.SpringApp.dto.UserSignUpRequestDto;
import com.sandbox.SpringApp.entity.RoleEntity;
import com.sandbox.SpringApp.entity.UserEntity;
import com.sandbox.SpringApp.enums.RoleEnum;
import com.sandbox.SpringApp.repository.RoleRepository;
import com.sandbox.SpringApp.repository.UserRepository;
import com.sandbox.SpringApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository
                           ){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserEntity registerUser(UserSignUpRequestDto dto) {
        RoleEntity roleUser = roleRepository.findByName(RoleEnum.USER);
        List<RoleEntity> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        UserEntity user = new UserEntity(dto);
        user.setRoles(userRoles);
        try{
            UserEntity registeredUser = userRepository.save(user);
            log.info("IN register - user: {} successfully registered", registeredUser);
            return registeredUser;
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User with email " + dto.getEmail() + " already exists."
            );
        }
    }
}
