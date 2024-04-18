package com.poebos.auth.infrastructure.oauth.provider;

import com.poebos.auth.domain.user.vo.User;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component("google")
public class GoogleProvider implements OAuthProvider {

  @Override
  public String getEmail(OidcUser oidcUser) {
    String email = oidcUser.getAttribute("email");

    if (email == null) {
      throw new IllegalArgumentException("Email is empty");
    }

    return email;
  }

  @Override
  public String getEmail(OAuth2User oAuth2User) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public User getUser(OidcUser oidcUser) {
    final String email = oidcUser.getAttribute("email");
    final String name = oidcUser.getAttribute("name");
    final String avatarUrl = oidcUser.getAttribute("avatar_url");
    String firstName = null;
    String secondName = null;

    if (name != null) {
      String[] parts = name.split(" ");
      firstName = parts[0];
      if (parts.length > 1) {
        secondName = parts[1];
      }
    }

    return User.builder()
               .email(email)
               .firstName(firstName)
               .secondName(secondName)
               .avatarUrl(avatarUrl)
               .build();
  }

  @Override
  public User getUser(OAuth2User oAuth2User) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
