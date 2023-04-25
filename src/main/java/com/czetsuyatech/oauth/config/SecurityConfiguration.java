package com.czetsuyatech.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(e -> e.disable())
        .httpBasic(e -> e.disable())
        .formLogin(e -> e.disable())
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/**")
            .fullyAuthenticated()
        )
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    ;
    return http.build();
  }

//  @Bean
//  public OpaqueTokenIntrospector introspector() {
//
//    return new GoogleTokenIntrospector("https://oauth2.googleapis.com/tokeninfo");
//  }
}
