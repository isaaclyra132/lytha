package com.isaaclyra.lytha_back.user.infrastructure.controller;

import com.isaaclyra.lytha_back.user.application.dto.CreateUserRequest;
import com.isaaclyra.lytha_back.user.application.service.CreateUserUseCase;
import com.isaaclyra.lytha_back.user.application.service.ListUsersUseCase;
import com.isaaclyra.lytha_back.user.application.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final ListUsersUseCase listUsersUseCase;

    public UserController(CreateUserUseCase createUserUseCase, ListUsersUseCase listUsersUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.listUsersUseCase = listUsersUseCase;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        UserResponse createdUser = createUserUseCase.execute(request);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> listUsers() {
        List<UserResponse> users = listUsersUseCase.execute();
        return ResponseEntity.ok(users);
    }
}
