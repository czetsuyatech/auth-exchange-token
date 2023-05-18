package com.czetsuyatech.oauth.web.config;

import com.czetsuyatech.oauth.web.exchange.BearerTokenInterceptor;
import com.czetsuyatech.oauth.web.exchange.BearerTokenWrapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcBeansConfig implements WebMvcConfigurer {

  @Bean
  public ObjectMapper objectMapper() {

    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    return objectMapper;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    // register the interceptor
    registry.addInterceptor(bearerTokenInterceptor());
    // you can exclude certain URL patterns here, for example
    // .excludePathPatterns("/health")
  }

  // the 2 methods below produces the bean for token wrapper and interceptor in request scope

  @Bean
  public BearerTokenInterceptor bearerTokenInterceptor() {
    return new BearerTokenInterceptor(bearerTokenWrapper());
  }

  @Bean
  @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
  public BearerTokenWrapper bearerTokenWrapper() {
    return new BearerTokenWrapper();
  }

  @Bean
  @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
  public RestTemplate restTemplate() {

    return null;
  }
}
