package com.czetsuyatech.google.api.persistence.repositories;

import com.czetsuyatech.google.api.persistence.entities.OauthTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OauthTokenRepository extends JpaRepository<OauthTokenEntity, Long> {

  Optional<OauthTokenEntity> findByUserId(Long userId);
}
