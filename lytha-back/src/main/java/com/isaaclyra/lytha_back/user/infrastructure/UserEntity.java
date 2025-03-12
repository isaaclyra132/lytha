package com.isaaclyra.lytha_back.user.infrastructure;

import com.isaaclyra.lytha_back.user.domain.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity(name = "user")
@Table(name = "USERS")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String password;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
