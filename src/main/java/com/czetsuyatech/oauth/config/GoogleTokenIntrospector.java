package com.czetsuyatech.oauth.config;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.BadOpaqueTokenException;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public final class GoogleTokenIntrospector implements OpaqueTokenIntrospector {

  private final RestTemplate restTemplate = new RestTemplate();
  private final String introspectionUri;

  public GoogleTokenIntrospector(String introspectionUri) {

    this.introspectionUri = introspectionUri;
  }

  @Override
  public OAuth2AuthenticatedPrincipal introspect(String token) {

    RequestEntity<?> requestEntity = buildRequest(token);
    try {
      ResponseEntity<Map<String, Object>> responseEntity = this.restTemplate.exchange(requestEntity,
          new ParameterizedTypeReference<>() {

          });

      int i = 0;
      // TODO: Create and return OAuth2IntrospectionAuthenticatedPrincipal based on response...
    } catch (Exception ex) {
      throw new BadOpaqueTokenException(ex.getMessage(), ex);
    }

    return null;
  }

  private RequestEntity<?> buildRequest(String token) {

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    body.add("access_token", token);

    return new RequestEntity<>(body, headers, HttpMethod.POST, URI.create(introspectionUri));
  }
}
