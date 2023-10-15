package com.czetsuyatech.google.api.web.controllers;

import com.czetsuyatech.google.api.services.oauth.OauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OauthController {

  private final OauthService oauthService;

  @GetMapping("/")
  public ModelAndView login() {

    ModelAndView model = new ModelAndView("navigation");

    return model;
  }

  @GetMapping("/oauth/actions/save")
  public ModelAndView saveToken() {

    log.info("Saving access token");

    ModelAndView model = new ModelAndView("navigation");

    oauthService.saveToken();

    return model;
  }

  @GetMapping("/oauth/actions/refresh-token")
  public ModelAndView refreshToken() {

    log.info("Refresh access token");

    ModelAndView model = new ModelAndView("navigation");

    oauthService.refreshAccessToken();

    return model;
  }
}
