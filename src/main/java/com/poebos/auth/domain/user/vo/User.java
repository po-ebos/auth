package com.poebos.auth.domain.user.vo;

import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record User(UUID id,
                   String username,
                   String email,
                   String firstName,
                   String secondName,
                   String avatarUrl,
                   List<RoleEnum> roles ) {
}
