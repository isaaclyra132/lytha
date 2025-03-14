package com.isaaclyra.lytha_back.user.infrastructure;

import com.isaaclyra.lytha_back.user.domain.User;
import com.isaaclyra.lytha_back.user.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = UserMapper.toEntity(user);
        UserEntity savedEntity = userJpaRepository.save(userEntity);
        return UserMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream()
                .map(UserMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {

    }
}
