package com.sandbox.SpringApp.repository;

import com.sandbox.SpringApp.entity.RevokedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RevokedTokenRepository extends JpaRepository<RevokedToken, UUID> {
    RevokedToken findByToken(String token);
}
