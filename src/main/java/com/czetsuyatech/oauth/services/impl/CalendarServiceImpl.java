package com.czetsuyatech.oauth.services.impl;

import com.czetsuyatech.oauth.clients.ClientFactory;
import com.czetsuyatech.oauth.clients.google.GoogleCalendarClient;
import com.czetsuyatech.oauth.services.CalendarService;
import com.czetsuyatech.oauth.services.TokenExchanger;
import com.czetsuyatech.oauth.web.exchange.BearerTokenWrapper;
import com.czetsuyatech.oauth.web.exchange.GoogleTokenExchange;
import com.czetsuyatech.oauth.web.exchange.TokenExchange;
import com.google.api.services.calendar.model.CalendarList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CalendarServiceImpl implements CalendarService, TokenExchanger {

  private final BearerTokenWrapper bearerTokenWrapper;
  private final GoogleTokenExchange googleTokenExchange;

  @Override
  public CalendarList getCalendarEntries(int maxResults) {

    GoogleCalendarClient googleCalendarClient = ClientFactory.googleCalendarClient(
        getToken(bearerTokenWrapper.getToken()));
    return googleCalendarClient.getCalendarEntries(10);
  }

  @Override
  public TokenExchange getTokenExchange() {
    return googleTokenExchange;
  }
}
