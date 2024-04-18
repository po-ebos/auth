package com.poebos.auth.application.service;

import com.poebos.auth.domain.user.repository.UserRepository;
import com.poebos.auth.domain.user.usecase.FindUserUseCase;
import com.poebos.auth.domain.user.usecase.SaveUserUseCase;
import com.poebos.auth.domain.user.vo.User;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements SaveUserUseCase, FindUserUseCase {

  private final UserRepository userRepository;

  @Override
  public Optional<User> findById(UUID id) {
    return userRepository.findById(id);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }
}
