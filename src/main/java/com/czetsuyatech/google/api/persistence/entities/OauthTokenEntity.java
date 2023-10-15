package com.czetsuyatech.google.api.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "oauth_token")
@Data
public class OauthTokenEntity extends BaseEntity {

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "access_token", length = 2048)
  private String accessToken;

  @Column(name = "refresh_token", length = 2048)
  private String refreshToken;

  @Column(name = "access_token_expiry")
  private Instant accessTokenExpiry;

  @Column(name = "refresh_token_expiry")
  private Instant refreshTokenExpiry;
}
