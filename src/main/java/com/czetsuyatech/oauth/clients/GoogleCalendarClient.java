package com.czetsuyatech.oauth.clients;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface GoogleCalendarClient extends Client {

  @GetExchange("/users/me/calendarList")
  String getCalendarEntries(@RequestParam int maxResults);
}
