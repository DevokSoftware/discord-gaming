package com.devok.games.geoguessr.api.model;

import java.time.OffsetDateTime;

public class Authentication {
    private String mapillaryCode;
    private String accessToken;
    private OffsetDateTime accessTokenExpirationDate;

    public String getMapillaryCode() {
        return mapillaryCode;
    }

    public void setMapillaryCode(String mapillaryCode) {
        this.mapillaryCode = mapillaryCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public OffsetDateTime getAccessTokenExpirationDate() {
        return accessTokenExpirationDate;
    }

    public void setAccessTokenExpirationDate(OffsetDateTime accessTokenExpirationDate) {
        this.accessTokenExpirationDate = accessTokenExpirationDate;
    }

    public boolean accessTokenAlreadyExpired() {
        return accessTokenExpirationDate.isBefore(OffsetDateTime.now());
    }
}
