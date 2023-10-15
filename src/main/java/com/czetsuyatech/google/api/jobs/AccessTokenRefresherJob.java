package com.czetsuyatech.google.api.jobs;

import com.czetsuyatech.google.api.persistence.repositories.OauthTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccessTokenRefresherJob {

  private final OauthTokenRepository oauthTokenRepository;

  @Scheduled(cron = "0 0 0/1 * * ?")
  public void refreshToken() {

    var oauthToken = oauthTokenRepository.findById(1L);
    var accessToken = oauthToken
        .orElseThrow(() -> new RuntimeException("Missing token"))
        .getAccessToken();
  }
}
