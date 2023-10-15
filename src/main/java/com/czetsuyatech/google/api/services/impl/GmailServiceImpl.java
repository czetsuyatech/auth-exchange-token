package com.czetsuyatech.google.api.services.impl;

import com.czetsuyatech.google.api.services.GmailService;
import com.czetsuyatech.google.api.services.oauth.TokenExchange;
import com.czetsuyatech.google.api.services.oauth.TokenExchanger;
import com.czetsuyatech.google.api.services.oauth.impl.GoogleTokenExchange;
import com.czetsuyatech.google.api.web.filters.AccessTokenWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Slf4j
@RequiredArgsConstructor
@Service
public class GmailServiceImpl implements GmailService, TokenExchanger {

  private final GoogleTokenExchange googleTokenExchange;
  private final AccessTokenWrapper accessTokenWrapper;
  private final ObjectMapper OBJECT_MAPPER;

  @Override
  public ListMessagesResponse getMessages(int maxResults) {

    try {
      return gmailClient().users()
          .messages()
          .list("me")
          .setMaxResults(10L)
          .execute();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  @SneakyThrows
  public Message getMessage(String id) {

    return gmailClient().users()
        .messages()
        .get("me", id)
        .execute();
  }

  private Gmail gmailClient() {

    var accessToken = accessTokenWrapper.getToken();
    var googleToken = getToken(accessToken);

    GoogleCredential credential = new GoogleCredential().setAccessToken(googleToken);
    final NetHttpTransport HTTP_TRANSPORT;

    try {
      HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

    } catch (IOException | GeneralSecurityException e) {
      throw new RuntimeException(e);
    }

    JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
        .build();
  }

  @Override
  public TokenExchange getTokenExchange() {
    return googleTokenExchange;
  }
}
