package com.czetsuyatech.oauth.web.controllers;

import com.czetsuyatech.oauth.web.exchange.GoogleToken;
import com.czetsuyatech.oauth.web.exchange.TokenExchange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CalendarController {

  private static final String GOOGLE_CALENDAR_V3_API = "https://www.googleapis.com/calendar/v3";

  @GoogleToken
  private final TokenExchange tokenExchange;

  @GetMapping("/calendars/entries")
  public String entries() {

    String url = GOOGLE_CALENDAR_V3_API + "/users/me/calendarList";
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(tokenExchange.exchangeToken());

    HttpEntity<String> entity = new HttpEntity<>(null, headers);
    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

    log.debug(response.getBody());

    return response.getBody();
  }
}
