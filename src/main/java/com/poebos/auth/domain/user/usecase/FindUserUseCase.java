package com.poebos.auth.domain.user.usecase;

import com.poebos.auth.domain.user.vo.User;
import java.util.Optional;
import java.util.UUID;

public interface FindUserUseCase {

  Optional<User> findById(UUID id);

  Optional<User> findByEmail(String email);

  Optional<User> findByUsername(String username);
}
