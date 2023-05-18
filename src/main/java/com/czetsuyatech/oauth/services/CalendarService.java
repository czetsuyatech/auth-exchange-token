package com.czetsuyatech.oauth.services;

import com.czetsuyatech.oauth.web.exchange.TokenExchange;

public interface CalendarService {

  String getCalendarEntries(TokenExchange tokenExchange, int maxResults);
}
