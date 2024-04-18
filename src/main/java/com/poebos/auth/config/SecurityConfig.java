package com.poebos.auth.config;

import com.poebos.auth.infrastructure.oauth.user.service.ApplicationOidcUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

  private final ApplicationOidcUserService applicationOidcUserService;

  //private final AuthenticationFailureHandler failureHandler;

  private final AuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();

  @Bean
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
        .oauth2Login(oauth2LoginCustomizer -> {
          oauth2LoginCustomizer.successHandler(successHandler);
          //oauth2LoginCustomizer.failureHandler(failureHandler);
          oauth2LoginCustomizer.userInfoEndpoint(userInfoEndpointConfig ->
                                                     userInfoEndpointConfig
                                                         .oidcUserService(applicationOidcUserService));
        });

    return http.formLogin(withDefaults()).build();
  }

  @Bean
  public UserDetailsService users() {
    UserDetails user = User.builder()
                           .username("admin")
                           .password("{noop}password")
                           .roles("USER")
                           .build();
    return new InMemoryUserDetailsManager(user);
  }
}
