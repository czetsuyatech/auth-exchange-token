package com.czetsuyatech.oauth.services.impl;

import com.czetsuyatech.oauth.clients.ClientFactory;
import com.czetsuyatech.oauth.clients.google.GoogleEmailClient;
import com.czetsuyatech.oauth.services.GmailService;
import com.czetsuyatech.oauth.services.TokenExchanger;
import com.czetsuyatech.oauth.web.exchange.BearerTokenWrapper;
import com.czetsuyatech.oauth.web.exchange.GoogleTokenExchange;
import com.czetsuyatech.oauth.web.exchange.TokenExchange;
import com.google.api.services.gmail.model.ListMessagesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GmailServiceImpl implements GmailService, TokenExchanger {

  private final BearerTokenWrapper bearerTokenWrapper;
  private final GoogleTokenExchange googleTokenExchange;

  @Override
  public ListMessagesResponse getMessages(int maxResults) {

    GoogleEmailClient googleEmailClient = ClientFactory.googleEmailClient(
        getToken(bearerTokenWrapper.getToken()));
    return googleEmailClient.getMessages(10);
  }

  @Override
  public TokenExchange getTokenExchange() {
    return googleTokenExchange;
  }
}
