package com.isaaclyra.lytha_back.user.application.service;

import com.isaaclyra.lytha_back.user.application.dto.CreateUserRequest;
import com.isaaclyra.lytha_back.user.application.dto.UserResponse;
import com.isaaclyra.lytha_back.user.domain.PasswordEncoder;
import com.isaaclyra.lytha_back.user.domain.Role;
import com.isaaclyra.lytha_back.user.domain.User;
import com.isaaclyra.lytha_back.user.domain.UserRepository;
import com.isaaclyra.lytha_back.user.infrastructure.UserMapper;

import java.util.Set;

public class CreateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCase (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse execute(CreateUserRequest request) {
        String hashedPassword = passwordEncoder.encode(request.password());

        User user = UserMapper.fromCreateUserRequest(request);
        user.setPassword(hashedPassword);
        user.setRoles(Set.of(Role.USER));

        User savedUser = userRepository.save(user);
        return UserMapper.toResponse(savedUser);
    }
}
