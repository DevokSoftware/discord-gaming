package com.devok.games.geoguessr.api.mapillary.authentication;

import com.devok.common.database.EMRepository;
import com.devok.games.geoguessr.api.mapillary.MapillaryService;
import com.devok.games.geoguessr.api.mapillary.authentication.mapper.AuthenticationMapper;
import com.devok.games.geoguessr.api.mapillary.authentication.model.AuthenticationDTO;
import com.devok.games.geoguessr.api.mapillary.authentication.model.TokenRequest;
import com.devok.games.geoguessr.services.images.model.Code;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@ApplicationScoped
public class AuthenticationService extends EMRepository<Code> {

    @Inject
    private MapillaryService mapillaryService;

    @Inject
    private AuthenticationMapper authenticationMapper;

    private AuthenticationDTO authenticationDTO;

    private static final String HEADER_AUTH = "OAuth " + System.getProperty("mapillary.clientToken");

    public AuthenticationService() {
        this.authenticationDTO = new AuthenticationDTO();
    }

    @Transactional
    public void updateMapillaryCode(String code) {
        Code mapillaryCode = getCode();
        if (mapillaryCode != null) {
            remove(mapillaryCode);
        }
        Code newCode = new Code(code);
        persist(newCode);
    }

    public void loadMapillaryCode() {
        Code code = getCode();
        if (code != null) {
            authenticationDTO.setMapillaryCode(code.getMapillaryCode());
        }
    }

    public Code getCode() {
        try {
            return createNamedQuery("Token.getTokens", Code.class).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public String getAccessToken() {
        loadMapillaryCode();
        if (authenticationDTO.getAccessToken() == null) {
            createAccessToken();
        } else if (authenticationDTO.accessTokenAlreadyExpired()) {
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
