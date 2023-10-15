package com.czetsuyatech.google.api.clients.google.flux;

import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface GoogleCalendarExchangeClient {

  @GetExchange("/users/me/calendarList")
  CalendarList getCalendarEntries(@RequestParam int maxResults);

  @GetExchange("/calendars/{calendarId}/events")
  Events getCalendarEvents(@PathVariable("calendarId") String calendarId, @RequestParam int maxResults);

  @GetExchange("/calendars/{calendarId}/events/{eventId}")
  Event getCalendarEventDetail(@PathVariable("calendarId") String calendarId, @PathVariable("eventId") String eventId);
}
