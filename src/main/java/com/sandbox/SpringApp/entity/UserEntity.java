package com.sandbox.SpringApp.entity;

import com.sandbox.SpringApp.dto.UserDto;
import com.sandbox.SpringApp.dto.UserSignUpRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @CreatedDate
    @Column(name = "created")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<RoleEntity> roles;

    public UserEntity(UserSignUpRequestDto dto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.email = dto.getEmail();
        this.password = passwordEncoder.encode(dto.getPassword());
        this.active = true;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    public UserDto toDto(){
        return new UserDto(
                this.id,
                this.email,
                this.created,
                this.updated,
                this.active,
                this.roles.stream().map(RoleEntity::toString).collect(Collectors.toList())
        );
    }
}
