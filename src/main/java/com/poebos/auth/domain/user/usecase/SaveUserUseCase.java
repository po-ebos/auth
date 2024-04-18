package com.poebos.auth.domain.user.usecase;

import com.poebos.auth.domain.user.vo.User;

public interface SaveUserUseCase {

  User save(User user);
}
