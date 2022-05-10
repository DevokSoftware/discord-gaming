package com.devok.games.geoguessr.api.common.mapper;

import com.devok.games.geoguessr.api.model.mapillary.Authentication;
import com.devok.games.geoguessr.api.model.mapillary.TokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.OffsetDateTime;

@Mapper(componentModel = "cdi")
public interface AuthenticationMapper {

    @Mapping(target = "accessTokenExpirationDate", expression = "java(calculateExpirationTime(tokenResponse.getExpiresIn()))")
    Authentication mapTokenResponseToAuthentication(TokenResponse tokenResponse);

    default OffsetDateTime calculateExpirationTime(Long expiresIn){
        return OffsetDateTime.now().plusSeconds(expiresIn);
    }
}
