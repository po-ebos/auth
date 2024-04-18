package com.poebos.auth.infrastructure.oauth.user.service;

import com.poebos.auth.application.service.UserService;
import com.poebos.auth.infrastructure.oauth.provider.OAuthProvider;
import com.poebos.auth.infrastructure.oauth.user.mapper.OAuthUserMapper;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationOidcUserService extends OidcUserService {

  private final Map<String, OAuthProvider> providers;

  private final OAuthUserMapper oAuthUserMapper;

  private final UserService userService;

  @Override
  public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
    OidcUser oidcUser = super.loadUser(userRequest);
    String clientRegId = userRequest.getClientRegistration().getRegistrationId();
    OAuthProvider oAuthProvider = providers.get(clientRegId);

    String email = oAuthProvider.getEmail(oidcUser);

    return userService.findByEmail(email)
                      .map(oAuthUserMapper::toOAuthUser)
                      .orElseGet(() -> oAuthUserMapper.toOAuthUser(
                          userService.save(oAuthProvider.getUser(oidcUser))));
  }
}
