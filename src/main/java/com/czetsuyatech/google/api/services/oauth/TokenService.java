package com.czetsuyatech.google.api.services.oauth;

import com.czetsuyatech.google.api.services.oauth.pojos.OauthToken;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public interface TokenService {

  default OauthToken getOauthToken() {

    OauthToken token = new OauthToken();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String accessToken = null;
    if (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
      OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
      String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();

      if (clientRegistrationId.equals("keycloak")) {
        OAuth2AuthorizedClient client =
            getClientService().loadAuthorizedClient(clientRegistrationId, oauthToken.getName());

        if (client != null) {
          token.setAccessToken(client.getAccessToken().getTokenValue());
          token.setRefreshToken(client.getRefreshToken().getTokenValue());
        }
      }
    }

    return token;
  }

  default OauthToken refreshToken(String refreshToken) {

    AuthzClient authzClient = AuthzClient.create();
    Configuration keycloakConfig = authzClient.getConfiguration();
    String baseUrl = authzClient.getServerConfiguration().getTokenEndpoint();

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
    headers.add("Accept", MediaType.APPLICATION_JSON.toString());

    MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    requestBody.add("client_id", keycloakConfig.getResource());
    requestBody.add("client_secret", keycloakConfig.getCredentials().get("secret").toString());
    requestBody.add("grant_type", "refresh_token");
    requestBody.add("refresh_token", refreshToken);

    HttpEntity formEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<AccessTokenResponse> response = restTemplate.exchange(baseUrl, HttpMethod.POST, formEntity,
        AccessTokenResponse.class);

    return OauthToken.builder()
        .accessToken(response.getBody().getToken())
        .refreshToken(response.getBody().getRefreshToken())
        .build();
  }

  OAuth2AuthorizedClientService getClientService();
}
