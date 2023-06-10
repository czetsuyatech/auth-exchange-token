package com.czetsuyatech.oauth.web.controllers;

import com.czetsuyatech.oauth.services.GmailService;
import com.google.api.services.gmail.model.ListMessagesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GmailController {

  private final GmailService gmailService;

  @GetMapping("/messages")
  public ListMessagesResponse entries() {

    return gmailService.getMessages(10);
  }
}
