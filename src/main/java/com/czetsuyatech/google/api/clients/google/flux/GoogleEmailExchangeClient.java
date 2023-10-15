package com.czetsuyatech.google.api.clients.google.flux;

import com.google.api.services.gmail.model.ListMessagesResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface GoogleEmailExchangeClient {

  @GetExchange("/users/me/messages")
  ListMessagesResponse getMessages(@RequestParam int maxResults);

  @GetExchange("/users/me/messages/{id}")
  String getMessage(@PathVariable("id") String id, @RequestParam("format") String format);
}
