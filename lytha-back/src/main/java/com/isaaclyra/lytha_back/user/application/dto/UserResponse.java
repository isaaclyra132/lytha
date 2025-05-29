package com.isaaclyra.lytha_back.user.application.dto;

import com.isaaclyra.lytha_back.user.domain.Role;

import java.util.Set;

public record UserResponse(
        String email,
        String name,
        Set<Role> roles) {
}
