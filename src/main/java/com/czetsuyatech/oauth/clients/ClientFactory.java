package com.czetsuyatech.oauth.clients;

import com.czetsuyatech.oauth.clients.google.GoogleCalendarClient;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

public class ClientFactory {

  private static final String GOOGLE_CALENDAR_V3_API = "https://www.googleapis.com/calendar/v3";

  public static GoogleCalendarClient googleCalendarClient(String googleToken) {

    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter
            .forClient(googleCalendarWebClient(googleToken)))
        .build();

    return factory.createClient(GoogleCalendarClient.class);
  }

  private static WebClient googleCalendarWebClient(String googleToken) {
    return WebClient.builder()
        .baseUrl(GOOGLE_CALENDAR_V3_API)
        .defaultHeaders(httpHeaders -> {
          httpHeaders.setBearerAuth(googleToken);
        })
        .build();
  }
}
