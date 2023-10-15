package com.czetsuyatech.google.api.clients.google.flux;

import java.time.Duration;

import com.czetsuyatech.google.api.clients.google.Constants;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.netty.http.client.HttpClient;

public class ClientFactory {

  public static GoogleCalendarExchangeClient googleCalendarClient(String googleToken) {

    return getFactory(Constants.GOOGLE_CALENDAR_V3_API, googleToken).createClient(GoogleCalendarExchangeClient.class);
  }

  public static GoogleEmailExchangeClient googleEmailClient(String googleToken) {

    return getFactory(Constants.GOOGLE_GMAIL_V3_API, googleToken).createClient(GoogleEmailExchangeClient.class);
  }

  private static HttpServiceProxyFactory getFactory(String baseUrl, String googleToken) {

    return HttpServiceProxyFactory.builder(
            WebClientAdapter.forClient(googleCalendarWebClient(baseUrl, googleToken)))
        .build();
  }

  private static HttpClient httpClient() {

    return HttpClient.create()
        .responseTimeout(Duration.ofSeconds(10));
  }

  private static WebClient googleCalendarWebClient(String baseUrl, String googleToken) {
    return WebClient.builder()
        .clientConnector(new ReactorClientHttpConnector(httpClient()))
        .baseUrl(baseUrl)
        .defaultHeaders(httpHeaders -> {
          httpHeaders.setBearerAuth(googleToken);
        })
        .build();
  }
}
