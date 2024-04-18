package com.poebos.auth.infrastructure.oauth.user.mapper;

import com.poebos.auth.domain.user.vo.User;
import com.poebos.auth.infrastructure.oauth.user.vo.OAuthUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OAuthUserMapper {

  default OAuthUser toOAuthUser(User user) {
    OAuthUser oAuthUser = new OAuthUser(user.username() == null ? user.email() : user.username());
    oAuthUser.setId(user.id());
    oAuthUser.setEmail(user.email());
    oAuthUser.setFirstName(user.firstName());
    oAuthUser.setSecondName(user.secondName());
    oAuthUser.setAvatarUrl(user.avatarUrl());

    return oAuthUser;
  }
}
