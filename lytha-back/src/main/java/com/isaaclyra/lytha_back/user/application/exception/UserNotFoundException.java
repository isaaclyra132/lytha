package com.isaaclyra.lytha_back.user.application.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("Usuãrio com ID " + id + " não encontrado.");
    }
}

