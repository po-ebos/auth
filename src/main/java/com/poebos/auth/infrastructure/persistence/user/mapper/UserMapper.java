package com.poebos.auth.infrastructure.persistence.user.mapper;

import com.poebos.auth.domain.user.vo.User;
import com.poebos.auth.infrastructure.persistence.user.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserEntity toEntity(User user);

  User toModel(UserEntity entity);
}
