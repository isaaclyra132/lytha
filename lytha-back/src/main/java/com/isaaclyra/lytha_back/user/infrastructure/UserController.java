package com.isaaclyra.lytha_back.user.infrastructure;

import com.isaaclyra.lytha_back.user.application.CreateUserRequest;
import com.isaaclyra.lytha_back.user.application.CreateUserUseCase;
import com.isaaclyra.lytha_back.user.application.ListUsersUseCase;
import com.isaaclyra.lytha_back.user.application.UserDTO;
import com.isaaclyra.lytha_back.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final ListUsersUseCase listUsersUseCase;

    public UserController(CreateUserUseCase createUserUseCase, ListUsersUseCase listUsersUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.listUsersUseCase = listUsersUseCase;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserRequest request) {
        UserDTO createdUser = createUserUseCase.execute(request);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> listUsers() {
        List<UserDTO> users = listUsersUseCase.execute();
        return ResponseEntity.ok(users);
    }
}
