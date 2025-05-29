package com.isaaclyra.lytha_back.user;

import com.isaaclyra.lytha_back.user.application.dto.CreateUserRequest;
import com.isaaclyra.lytha_back.user.application.service.CreateUserUseCase;
import com.isaaclyra.lytha_back.user.application.dto.UserResponse;
import com.isaaclyra.lytha_back.user.domain.PasswordEncoder;
import com.isaaclyra.lytha_back.user.domain.Role;
import com.isaaclyra.lytha_back.user.domain.User;
import com.isaaclyra.lytha_back.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @Test
    void shouldCreateUserWithHashedPassword() {
        // Arrange
        String rawPassword = "securePassword123";
        String hashedPassword = "hashedPassword";
        CreateUserRequest request = new CreateUserRequest("John Doe", "john_doe@email.com", rawPassword);
        when(passwordEncoder.encode(rawPassword)).thenReturn(hashedPassword);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(1L);
            return user;
        });

        UserResponse result = createUserUseCase.execute(request);

        // Assert: Verifica o DTO retornado
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("john_doe@email.com", result.email());
        assertEquals("John Doe", result.name());
        assertEquals(Set.of(Role.USER), result.roles());

        // Verifica se a senha foi hasheada antes de salvar
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertEquals(hashedPassword, savedUser.getPassword()); // Senha hasheada
        verify(passwordEncoder).encode(rawPassword); // Garante que o encode foi chamado
    }

    @Test
    void shouldThrowInvalidUserExceptionWhenUsernameIsNull() {}

    @Test
    void shouldThrowDuplicateUserExceptionWhenUsernameAlreadyExists() {}

    @Test
    void shouldPropagateException_whenRepositoryFailsToSaveUser() {}
}
