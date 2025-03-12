package com.isaaclyra.lytha_back.user.application;

import java.util.List;

import com.isaaclyra.lytha_back.user.domain.User;
import com.isaaclyra.lytha_back.user.domain.UserRepository;

public class ListUsersUseCase {
    private final UserRepository userRepository;

    public ListUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> execute() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getEmail(),
                        user.getName(),
                        user.getRoles()
                )).toList();
    }
}
