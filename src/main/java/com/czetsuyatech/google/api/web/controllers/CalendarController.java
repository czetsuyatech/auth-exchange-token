package com.czetsuyatech.google.api.web.controllers;

import com.czetsuyatech.google.api.services.CalendarService;
import com.czetsuyatech.google.api.services.oauth.impl.OauthServiceImpl;
import com.czetsuyatech.google.api.web.filters.AccessTokenWrapper;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CalendarController {

  private final CalendarService calendarService;
  private final OauthServiceImpl oauthService;
  private final AccessTokenWrapper tokenWrapper;

  @GetMapping("/calendar-entries")
  public ModelAndView entries() {

    var token = oauthService.refreshAccessToken();
    tokenWrapper.setToken(token);

    ModelAndView model = new ModelAndView("views/calendarEntries");

    CalendarList calendarList = calendarService.getCalendarEntries(10);

    log.debug("{}", calendarList);

    model.addObject("calendarEntry", calendarList);

    return model;
  }

  @GetMapping("/calendar-events/{calendarId}")
  public ModelAndView events(@PathVariable("calendarId") String calendarId) {

    var token = oauthService.refreshAccessToken();
    tokenWrapper.setToken(token);

    ModelAndView model = new ModelAndView("views/calendarEvents");

    Events calendarEvents = calendarService.getCalendarEvents(calendarId);

    log.debug("{}", calendarEvents);

    model.addObject("calendarEvents", calendarEvents);
    model.addObject("calendarId", calendarId);

    return model;
  }

  @GetMapping("/calendar-events/{calendarId}/events/{eventId}")
  public ModelAndView eventDetail(@PathVariable("calendarId") String calendarId, @PathVariable("eventId") String eventId) {

    var token = oauthService.refreshAccessToken();
    tokenWrapper.setToken(token);

    ModelAndView model = new ModelAndView("views/calendarEventDetail");

    Event calendarEvent = calendarService.getCalendarEventDetail(calendarId, eventId);

    log.debug("{}", calendarEvent);

    model.addObject("calendarEvent", calendarEvent);

    return model;
  }
}
