package com.devok.games.geoguessr.api.model;

import javax.json.bind.annotation.JsonbProperty;

public class TokenResponse {
    @JsonbProperty("access_token")
    private String accessToken;

    @JsonbProperty("expires_in")
    private String expiresIn;

    @JsonbProperty("token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
