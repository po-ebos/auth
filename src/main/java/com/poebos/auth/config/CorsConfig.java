package com.poebos.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Slf4j
@Configuration
public class CorsConfig {

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();

    config.setAllowCredentials(true);

    config.addAllowedOrigin("http://127.0.0.1:8080,http://localhost:3000");
    config.addAllowedHeader(CorsConfiguration.ALL);
    config.addExposedHeader(CorsConfiguration.ALL);
    config.addAllowedMethod(CorsConfiguration.ALL);

    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}
