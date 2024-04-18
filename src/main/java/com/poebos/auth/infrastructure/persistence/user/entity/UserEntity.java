package com.poebos.auth.infrastructure.persistence.user.entity;

import com.poebos.auth.domain.user.vo.RoleEnum;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

  private UUID id;

  private String username;

  private String email;

  private String firstName;

  private String secondName;

  private String avatarUrl;

  private List<RoleEnum> roles;
}
