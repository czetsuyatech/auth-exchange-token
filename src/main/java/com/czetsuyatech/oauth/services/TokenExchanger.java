package com.czetsuyatech.oauth.services;

import com.czetsuyatech.oauth.web.exchange.TokenExchange;

public interface TokenExchanger {

  default String getToken(String keycloakToken) {
    return getTokenExchange().exchangeToken(keycloakToken);
  }

  TokenExchange getTokenExchange();
}
