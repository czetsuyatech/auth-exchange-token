package com.czetsuyatech.google.api.mappers;

import com.czetsuyatech.google.api.persistence.entities.OauthTokenEntity;
import com.czetsuyatech.google.api.services.oauth.pojos.OauthToken;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface Service2EntityMapper {

  OauthTokenEntity asEntity(OauthToken token);

  OauthTokenEntity asEntity(OauthToken oauthToken, @MappingTarget OauthTokenEntity entity);
}
