package com.isaaclyra.lytha_back.user.config;

import com.isaaclyra.lytha_back.user.application.service.CreateUserUseCase;
import com.isaaclyra.lytha_back.user.application.service.ListUsersUseCase;
import com.isaaclyra.lytha_back.user.domain.PasswordEncoder;
import com.isaaclyra.lytha_back.user.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new CreateUserUseCase(userRepository, passwordEncoder);
    }

    @Bean
    public ListUsersUseCase listUsersUseCase(UserRepository userRepository) {
        return new ListUsersUseCase(userRepository);
    }
}
