package com.czetsuyatech.google.api.web.controllers;

import com.czetsuyatech.google.api.services.GmailService;
import com.czetsuyatech.google.api.services.oauth.impl.OauthServiceImpl;
import com.czetsuyatech.google.api.web.filters.AccessTokenWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GmailController {

  private final GmailService gmailService;
  private final OauthServiceImpl oauthService;
  private final AccessTokenWrapper tokenWrapper;

  @GetMapping("/email-messages")
  public ModelAndView emailMessages() {

    var token = oauthService.refreshAccessToken();
    tokenWrapper.setToken(token);

    ModelAndView model = new ModelAndView("views/emailMessages");

    var emailMessages = gmailService.getMessages(10);

    log.debug("{}", emailMessages);

    model.addObject("email", emailMessages);

    return model;
  }

  @GetMapping("/email-messages/{id}")
  public ModelAndView emailMessages(@PathVariable("id") String id) {

    var token = oauthService.refreshAccessToken();
    tokenWrapper.setToken(token);

    ModelAndView model = new ModelAndView("views/emailMessage");

    var emailMessage = gmailService.getMessage(id);

    log.debug("{}", emailMessage);

    model.addObject("message", emailMessage);

    return model;
  }
}
