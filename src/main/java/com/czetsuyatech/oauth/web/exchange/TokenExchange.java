package com.czetsuyatech.oauth.web.exchange;

public interface TokenExchange {

  String exchangeToken(String accessToken);
}
