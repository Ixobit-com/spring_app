package com.sandbox.SpringApp.security.jwt;

import com.sandbox.SpringApp.entity.RoleEntity;
import com.sandbox.SpringApp.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(UserEntity user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
                user.getActive()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleEntity> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName().toString())
                ).collect(Collectors.toList());
    }
}
