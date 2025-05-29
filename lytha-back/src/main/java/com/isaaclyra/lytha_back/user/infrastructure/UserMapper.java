package com.isaaclyra.lytha_back.user.infrastructure;

import com.isaaclyra.lytha_back.user.application.dto.UserResponse;
import com.isaaclyra.lytha_back.user.domain.User;
import com.isaaclyra.lytha_back.user.infrastructure.repository.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserEntity toEntity(User user) {
        return new UserEntity(user.getId(), user.getEmail(), user.getName(), user.getPassword(), user.getRoles());
    }

    public static User toDomain(UserEntity entity) {
        return new User(entity.getId(), entity.getEmail(), entity.getPassword(), entity.getName(), entity.getRoles());
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getEmail(), user.getName(), user.getRoles());
    }
}
