package com.czetsuyatech.oauth.services.impl;

import com.czetsuyatech.oauth.clients.ClientFactory;
import com.czetsuyatech.oauth.clients.GoogleCalendarClient;
import com.czetsuyatech.oauth.services.CalendarService;
import com.czetsuyatech.oauth.web.exchange.GoogleToken;
import com.czetsuyatech.oauth.web.exchange.TokenExchange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CalendarServiceImpl implements CalendarService {

  @Override
  public String getCalendarEntries(TokenExchange tokenExchange, int maxResults) {

    GoogleCalendarClient googleCalendarClient = ClientFactory.googleCalendarClient(tokenExchange.exchangeToken());
    return googleCalendarClient.getCalendarEntries(10);
  }
}
