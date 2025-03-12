package com.isaaclyra.lytha_back.user.application;

import com.isaaclyra.lytha_back.user.domain.Role;

import java.util.Set;

public record UserDTO(
        Long id,
        String email,
        String name,
        Set<Role> roles) {
}
