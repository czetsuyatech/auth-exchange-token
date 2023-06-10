package com.czetsuyatech.oauth.clients;

import com.czetsuyatech.oauth.clients.google.GoogleCalendarClient;
import com.czetsuyatech.oauth.clients.google.GoogleEmailClient;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

public class ClientFactory {

  private static final String GOOGLE_CALENDAR_V3_API = "https://www.googleapis.com/calendar/v3";
  private static final String GOOGLE_GMAIL_V3_API = "https://gmail.googleapis.com/gmail/v1";

  public static GoogleCalendarClient googleCalendarClient(String googleToken) {

    return getFactory(GOOGLE_CALENDAR_V3_API, googleToken).createClient(GoogleCalendarClient.class);
  }

  public static GoogleEmailClient googleEmailClient(String googleToken) {

    return getFactory(GOOGLE_GMAIL_V3_API, googleToken).createClient(GoogleEmailClient.class);
  }

  private static HttpServiceProxyFactory getFactory(String baseUrl, String googleToken) {

    return HttpServiceProxyFactory.builder(
            WebClientAdapter.forClient(googleCalendarWebClient(baseUrl, googleToken)))
        .build();
  }

  private static WebClient googleCalendarWebClient(String baseUrl, String googleToken) {
    return WebClient.builder()
        .baseUrl(baseUrl)
        .defaultHeaders(httpHeaders -> {
          httpHeaders.setBearerAuth(googleToken);
        })
        .build();
  }
}
