package com.isaaclyra.lytha_back.user.infrastructure;

import com.isaaclyra.lytha_back.user.domain.User;

public class UserMapper {

    // Converte User (domínio) para UserEntity (persistência)
    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setEmail(user.getEmail());
        entity.setName(user.getName());
        entity.setPassword(user.getPassword());
        entity.setRoles(user.getRoles());
        return entity;
    }

    // Converte UserEntity (persistência) para User (domínio)
    public static User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setEmail(entity.getEmail());
        user.setName(entity.getName());
        user.setPassword(entity.getPassword());
        user.setRoles(entity.getRoles());
        return user;
    }
}
