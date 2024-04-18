package com.poebos.auth.domain.user.repository;

import com.poebos.auth.domain.user.vo.User;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

  User save(User user);

  Optional<User> findById(UUID id);

  Optional<User> findByEmail(String email);

  Optional<User> findByUsername(String username);
}
