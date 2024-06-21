package com.sandbox.SpringApp.repository;

import com.sandbox.SpringApp.entity.RoleEntity;
import com.sandbox.SpringApp.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByName(RoleEnum name);
}
