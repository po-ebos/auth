package com.poebos.auth.infrastructure.persistence.user.repository;

import com.poebos.auth.domain.user.repository.UserRepository;
import com.poebos.auth.domain.user.vo.User;
import com.poebos.auth.infrastructure.persistence.user.entity.UserEntity;
import com.poebos.auth.infrastructure.persistence.user.mapper.UserMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final static List<UserEntity> STORE = new ArrayList<>();

  private final UserMapper userMapper;

  @Override
  public User save(User user) {
    var entity = userMapper.toEntity(user);
    if (entity.getId() == null) {
      entity.setId(UUID.randomUUID());
    }
    STORE.add(entity);
    return userMapper.toModel(entity);
  }

  @Override
  public Optional<User> findById(UUID id) {
    return STORE.stream()
                .filter(entity -> id.equals(entity.getId()))
                .map(userMapper::toModel)
                .findFirst();
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return STORE.stream()
                .filter(entity -> email.equals(entity.getEmail()))
                .map(userMapper::toModel)
                .findFirst();
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return STORE.stream()
                .filter(entity -> username.equals(entity.getUsername()))
                .map(userMapper::toModel)
                .findFirst();
  }
}
