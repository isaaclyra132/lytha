package com.isaaclyra.lytha_back.user.application.dto;

public record CreateUserRequest(
        String name,
        String email,
        String password) {

}
