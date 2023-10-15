package com.czetsuyatech.google.api.services.impl;

import com.czetsuyatech.google.api.services.CalendarService;
import com.czetsuyatech.google.api.services.oauth.TokenExchange;
import com.czetsuyatech.google.api.services.oauth.TokenExchanger;
import com.czetsuyatech.google.api.services.oauth.impl.GoogleTokenExchange;
import com.czetsuyatech.google.api.web.filters.AccessTokenWrapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Slf4j
@RequiredArgsConstructor
@Service
public class CalendarServiceImpl implements CalendarService, TokenExchanger {

  private final GoogleTokenExchange googleTokenExchange;
  private final AccessTokenWrapper accessTokenWrapper;

  @Override
  public CalendarList getCalendarEntries(int maxResults) {

    try {
      return calendarClient().calendarList()
          .list()
          .execute();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Events getCalendarEvents(String calendarId) {

    try {
      return calendarClient().events()
          .list(calendarId)
          .setMaxResults(10)
          .execute();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Event getCalendarEventDetail(String calendarId, String eventId) {

    try {
      return calendarClient().events()
          .get(calendarId, eventId)
          .execute();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Calendar calendarClient() {

    var accessToken = accessTokenWrapper.getToken();
    var googleToken = getToken(accessToken);

    GoogleCredential credential = new GoogleCredential().setAccessToken(googleToken);
    final NetHttpTransport HTTP_TRANSPORT;

    try {
      HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

    } catch (IOException | GeneralSecurityException e) {
      throw new RuntimeException(e);
    }

    JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
        .build();
  }

  @Override
  public TokenExchange getTokenExchange() {
    return googleTokenExchange;
  }
}
