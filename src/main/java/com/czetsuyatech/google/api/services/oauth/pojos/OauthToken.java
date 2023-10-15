package com.czetsuyatech.google.api.services.oauth.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OauthToken {

  private String accessToken;
  private String refreshToken;
  private Instant accessTokenExpiry;
  private Instant refreshTokenExpiry;
}
