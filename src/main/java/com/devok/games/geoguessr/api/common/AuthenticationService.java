package com.devok.games.geoguessr.api.common;

import com.devok.games.geoguessr.api.MapillaryService;
import com.devok.games.geoguessr.api.common.mapper.AuthenticationMapper;
import com.devok.games.geoguessr.api.model.mapillary.Authentication;
import com.devok.games.geoguessr.api.model.mapillary.TokenRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AuthenticationService {

    @Inject
    private MapillaryService mapillaryService;

    @Inject
    private AuthenticationMapper authenticationMapper;

    private Authentication authentication;

    private static final String HEADER_AUTH = "OAuth " + System.getProperty("mapillary.clientToken");

    public AuthenticationService() {
        this.authentication = new Authentication();
    }

    public void updateMapillaryCode(String mapillaryCode) {
        authentication.setMapillaryCode(mapillaryCode);
    }

    public String getAccessToken() {
        if (authentication.getAccessToken() == null ) {
            createAccessToken();
        }else if(authentication.accessTokenAlreadyExpired()){
            refreshAccessToken();
        }
        return authentication.getAccessToken();
    }

    private void createAccessToken() {
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setClient_id(System.getProperty("mapillary.clientId"));
        tokenRequest.setGrant_type("authorization_code");
        tokenRequest.setCode(authentication.getMapillaryCode());
        authentication = authenticationMapper.mapTokenResponseToAuthentication(mapillaryService.getAccessToken(HEADER_AUTH, tokenRequest));
    }

    private void refreshAccessToken() {
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setClient_id(System.getProperty("mapillary.clientId"));
        tokenRequest.setGrant_type("refresh_token");
        tokenRequest.setRefresh_token(authentication.getAccessToken());
        authentication = authenticationMapper.mapTokenResponseToAuthentication(mapillaryService.getAccessToken(HEADER_AUTH, tokenRequest));
    }
}
