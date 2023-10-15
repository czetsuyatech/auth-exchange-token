package com.czetsuyatech.google.api.services;

import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

public interface CalendarService {

  CalendarList getCalendarEntries(int maxResults);

  Events getCalendarEvents(String calendarId);

  Event getCalendarEventDetail(String calendarId, String eventId);
}
