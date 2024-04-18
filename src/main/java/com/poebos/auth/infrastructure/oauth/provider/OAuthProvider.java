package com.poebos.auth.infrastructure.oauth.provider;

import com.poebos.auth.domain.user.vo.User;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface OAuthProvider {

  String getEmail(OidcUser oidcUser);

  String getEmail(OAuth2User oAuth2User);

  User getUser(OidcUser oidcUser);

  User getUser(OAuth2User oAuth2User);
}
