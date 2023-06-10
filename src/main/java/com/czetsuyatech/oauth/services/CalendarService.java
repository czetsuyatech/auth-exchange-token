package com.czetsuyatech.oauth.services;

import com.google.api.services.calendar.model.CalendarList;

public interface CalendarService {

  CalendarList getCalendarEntries(int maxResults);
}
