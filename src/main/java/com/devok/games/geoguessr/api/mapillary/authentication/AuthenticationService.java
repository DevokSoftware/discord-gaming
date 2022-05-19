package com.devok.games.geoguessr.api.mapillary.authentication;

import com.devok.games.geoguessr.api.MapillaryService;
import com.devok.games.geoguessr.api.mapillary.authentication.mapper.AuthenticationMapper;
import com.devok.games.geoguessr.api.mapillary.authentication.model.AuthenticationDTO;
import com.devok.games.geoguessr.api.mapillary.authentication.model.TokenRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AuthenticationService {

    @Inject
    private MapillaryService mapillaryService;

    @Inject
    private AuthenticationMapper authenticationMapper;

    private AuthenticationDTO authenticationDTO;

    private static final String HEADER_AUTH = "OAuth " + System.getProperty("mapillary.clientToken");

    public AuthenticationService() {
        this.authenticationDTO = new AuthenticationDTO();
    }

    public void updateMapillaryCode(String mapillaryCode) {
        authenticationDTO.setMapillaryCode(mapillaryCode);
    }

    public String getAccessToken() {
        if (authenticationDTO.getAccessToken() == null ) {
            createAccessToken();
        }else if(authenticationDTO.accessTokenAlreadyExpired()){
            refreshAccessToken();
        }
        return authenticationDTO.getAccessToken();
    }

    private void createAccessToken() {
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setClient_id(System.getProperty("mapillary.clientId"));
        tokenRequest.setGrant_type("authorization_code");
        tokenRequest.setCode(authenticationDTO.getMapillaryCode());
        authenticationDTO = authenticationMapper.mapTokenResponseToAuthentication(mapillaryService.getAccessToken(HEADER_AUTH, tokenRequest));
    }

    private void refreshAccessToken() {
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setClient_id(System.getProperty("mapillary.clientId"));
        tokenRequest.setGrant_type("refresh_token");
        tokenRequest.setRefresh_token(authenticationDTO.getAccessToken());
        authenticationDTO = authenticationMapper.mapTokenResponseToAuthentication(mapillaryService.getAccessToken(HEADER_AUTH, tokenRequest));
    }
}
