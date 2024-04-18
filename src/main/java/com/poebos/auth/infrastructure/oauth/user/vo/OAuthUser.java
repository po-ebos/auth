package com.poebos.auth.infrastructure.oauth.user.vo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
@Setter
@ToString(callSuper = true)
public class OAuthUser extends User implements OAuth2User, OidcUser {

  private UUID id;

  private String email;

  private String firstName;

  private String secondName;

  private String avatarUrl;

  public OAuthUser(String username) {
    super(username, "", true, true, true, true, List.of());
  }

  public OAuthUser(
      UUID id,
      String username,
      String email,
      String firstName,
      String secondName,
      String avatarUrl,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, "", true, true, true, true, authorities);
    this.id = id;
    this.email = email;
    this.firstName = firstName;
    this.secondName = secondName;
    this.avatarUrl = avatarUrl;
  }

  public void setUsername() {

  }

  @Override
  public Map<String, Object> getAttributes() {
    return toMap();
  }

  @Override
  public String getName() {
    return this.getUsername();
  }

  @Override
  public Map<String, Object> getClaims() {
    return toMap();
  }

  @Override
  public OidcUserInfo getUserInfo() {
    return new OidcUserInfo(toMap());
  }

  @Override
  public OidcIdToken getIdToken() {
    return null;
  }

  private Map<String, Object> toMap() {
    return Map.of(
        "id", id,
        "username", this.getUsername(),
        "email", email,
        "firstName", firstName,
        "secondName", secondName,
        "avatarUrl", avatarUrl,
        "authorities", this.getAuthorities());
  }
}
