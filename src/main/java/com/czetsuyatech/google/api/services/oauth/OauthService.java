package com.czetsuyatech.google.api.services.oauth;

import com.czetsuyatech.google.api.services.oauth.pojos.OauthToken;

public interface OauthService {

  void saveToken();

  void createOrUpdate(OauthToken oauthToken);

  String refreshAccessToken();
}
