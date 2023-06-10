package com.czetsuyatech.oauth.clients.google;

import com.google.api.services.gmail.model.ListMessagesResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface GoogleEmailClient {

  @GetExchange("/users/me/messages")
  ListMessagesResponse getMessages(@RequestParam int maxResults);
}
