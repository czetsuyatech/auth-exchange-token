package com.czetsuyatech.oauth.clients.google;

import com.google.api.services.calendar.model.CalendarList;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface GoogleCalendarClient {

  @GetExchange("/users/me/calendarList")
  CalendarList getCalendarEntries(@RequestParam int maxResults);
}
