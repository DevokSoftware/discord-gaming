package com.devok.games.geoguessr.api.mapillary.authentication.mapper;

import com.devok.games.geoguessr.api.mapillary.authentication.model.AuthenticationDTO;
import com.devok.games.geoguessr.api.mapillary.authentication.model.TokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.OffsetDateTime;

@Mapper(componentModel = "cdi")
public interface AuthenticationMapper {

    @Mapping(target = "accessTokenExpirationDate", expression = "java(calculateExpirationTime(tokenResponse.getExpiresIn()))")
    AuthenticationDTO mapTokenResponseToAuthentication(TokenResponse tokenResponse);

    default OffsetDateTime calculateExpirationTime(Long expiresIn){
        return OffsetDateTime.now().plusSeconds(expiresIn);
    }
}
