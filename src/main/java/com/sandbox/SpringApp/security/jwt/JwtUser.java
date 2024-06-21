package com.sandbox.SpringApp.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

public class JwtUser implements UserDetails {
    private final UUID id;
    private final String username;
    private final String password;
    private final Boolean active;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(
            UUID id,
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            Boolean active
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.active = active;
    }

    @JsonIgnore
    public UUID getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
