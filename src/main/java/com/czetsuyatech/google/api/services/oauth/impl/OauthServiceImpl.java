package com.czetsuyatech.google.api.services.oauth.impl;

import com.czetsuyatech.google.api.mappers.Service2EntityMapper;
import com.czetsuyatech.google.api.persistence.entities.OauthTokenEntity;
import com.czetsuyatech.google.api.persistence.repositories.OauthTokenRepository;
import com.czetsuyatech.google.api.services.oauth.OauthService;
import com.czetsuyatech.google.api.services.oauth.TokenService;
import com.czetsuyatech.google.api.services.oauth.pojos.OauthToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OauthServiceImpl implements OauthService, TokenService {

  private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
  private final Service2EntityMapper service2EntityMapper;
  private final OauthTokenRepository oauthTokenRepository;
  public static final long USER_ID = 1L;

  @Override
  @Transactional
  public void saveToken() {

    var oauthToken = getOauthToken();
    createOrUpdate(oauthToken);
  }

  @Override
  @Transactional
  public void createOrUpdate(OauthToken oauthToken) {

    log.debug("Saving token={}", oauthToken);

    var token = oauthTokenRepository.findByUserId(USER_ID);
    OauthTokenEntity entity = null;
    if (token.isPresent()) {
      entity = token.get();
      entity = service2EntityMapper.asEntity(oauthToken, entity);

    } else {
      entity = service2EntityMapper.asEntity(oauthToken);
    }
    entity.setUserId(USER_ID);
    oauthTokenRepository.save(entity);
  }

  @Override
  public String refreshAccessToken() {

    var token = oauthTokenRepository.findByUserId(USER_ID);

    if (token.isPresent()) {
      var entity = token.get();
      var refreshToken = refreshToken(token.get().getRefreshToken());
      entity.setAccessToken(refreshToken.getAccessToken());
      return entity.getAccessToken();
    }

    return null;
  }

  @Override
  public OAuth2AuthorizedClientService getClientService() {
    return oAuth2AuthorizedClientService;
  }
}
