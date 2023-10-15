package com.czetsuyatech.google.api.configs;

import com.czetsuyatech.google.api.web.filters.AccessTokenWrapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcBeansConfig implements WebMvcConfigurer {

  final OAuth2AuthorizedClientService clientService;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
  }

  @Bean
  public ObjectMapper objectMapper() {

    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    return objectMapper;
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
  public AccessTokenWrapper accessTokenWrapper() {
    return new AccessTokenWrapper();
  }
}
