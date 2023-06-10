package com.czetsuyatech.oauth.web.controllers;

import com.czetsuyatech.oauth.services.CalendarService;
import com.google.api.services.calendar.model.CalendarList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CalendarController {

  private final CalendarService calendarService;

  @GetMapping("/calendars/entries")
  public CalendarList entries() {

    CalendarList response = calendarService.getCalendarEntries(10);

    return response;
  }
}
