package com.isaaclyra.lytha_back.user.application;

public record CreateUserRequest(
        String name,
        String email,
        String password) {

}
