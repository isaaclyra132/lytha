package com.isaaclyra.lytha_back.user.application.service;

import java.util.List;

import com.isaaclyra.lytha_back.user.application.dto.UserResponse;
import com.isaaclyra.lytha_back.user.domain.User;
import com.isaaclyra.lytha_back.user.domain.UserRepository;

public class ListUsersUseCase {
    private final UserRepository userRepository;

    public ListUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> execute() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getName(),
                        user.getRoles()
                )).toList();
    }
}
