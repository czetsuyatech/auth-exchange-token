package com.czetsuyatech.oauth.web.exchange;

import lombok.RequiredArgsConstructor;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@GoogleToken
@Component
@RequiredArgsConstructor
public class GoogleTokenExchanger implements TokenExchange {

  private final BearerTokenWrapper bearerTokenWrapper;

  @Override
  public String exchangeToken() {

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
    requestBody.add("grant_type", "urn:ietf:params:oauth:grant-type:token-exchange");
    requestBody.add("subject_token_type", "urn:ietf:params:oauth:token-type:access_token");
    requestBody.add("subject_token", bearerTokenWrapper.getToken());
    requestBody.add("requested_issuer", "google");

    HttpEntity formEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<AccessTokenResponse> response = restTemplate.exchange(baseUrl, HttpMethod.POST, formEntity,
        AccessTokenResponse.class);

    return response.getBody().getToken();
  }
}
