package com.czetsuyatech.google.api.services.oauth;

public interface TokenExchanger {

  default String getToken(String keycloakToken) {
    return getTokenExchange().exchangeToken(keycloakToken);
  }

  TokenExchange getTokenExchange();
}
