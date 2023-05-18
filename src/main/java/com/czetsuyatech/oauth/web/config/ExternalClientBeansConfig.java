package com.czetsuyatech.oauth.web.config;

import com.czetsuyatech.oauth.clients.GoogleCalendarClient;
import com.czetsuyatech.oauth.web.exchange.GoogleToken;
import com.czetsuyatech.oauth.web.exchange.TokenExchange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ExternalClientBeansConfig {

  private static final String GOOGLE_CALENDAR_V3_API = "https://www.googleapis.com/calendar/v3";

//  @GoogleToken
//  private final TokenExchange tokenExchange;

  @Bean
  GoogleCalendarClient googleCalendarClient(@GoogleToken TokenExchange tokenExchange) {

    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter
            .forClient(googleCalendarWebClient(tokenExchange)))
        .build();
    return factory.createClient(GoogleCalendarClient.class);
  }

  WebClient googleCalendarWebClient(TokenExchange tokenExchange) {
    return WebClient.builder()
        .baseUrl(GOOGLE_CALENDAR_V3_API)
//        .defaultHeaders
        .filter((request, next) -> next.exchange(
            withBearerAuth(request, tokenExchange.exchangeToken())))
        .build();
  }

  private static ClientRequest withBearerAuth(ClientRequest request, String token) {
    return ClientRequest.from(request)
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        .build();
  }

//  private void addDefaultHeaders(final HttpHeaders headers) {
//    headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
//    headers.add(HttpHeaders.ACCEPT, "application/json");
//    headers.setBearerAuth(tokenExchange.exchangeToken());
//  }
}
