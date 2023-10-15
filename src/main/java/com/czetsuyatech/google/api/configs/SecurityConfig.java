package com.czetsuyatech.google.api.configs;


import com.czetsuyatech.google.api.keycloak.CustomJwtAuthenticationConverter;
import com.czetsuyatech.google.api.keycloak.KeycloakLogoutHandler;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final KeycloakLogoutHandler keycloakLogoutHandler;

  SecurityConfig(KeycloakLogoutHandler keycloakLogoutHandler) {
    this.keycloakLogoutHandler = keycloakLogoutHandler;
  }

  @Bean
  protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
    return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    return http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/**")
            .authenticated()
            .anyRequest()
            .permitAll())
        .oauth2ResourceServer(rs -> rs.jwt(j -> j.jwtAuthenticationConverter(new CustomJwtAuthenticationConverter())))
        .logout(handler -> handler.addLogoutHandler(keycloakLogoutHandler))
//        .oauth2Client(Customizer.withDefaults())
        .oauth2Login(Customizer.withDefaults())
        .build()
        ;
  }

  @Bean
  public JwtDecoder jwtDecoder(OAuth2ResourceServerProperties oAuth2ResourceServerProperties) {

    NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(oAuth2ResourceServerProperties.getJwt().getJwkSetUri())
        .build();
    jwtDecoder.setJwtValidator(
        JwtValidators.createDefaultWithIssuer(oAuth2ResourceServerProperties.getJwt().getIssuerUri()));

    return jwtDecoder;
  }
}
