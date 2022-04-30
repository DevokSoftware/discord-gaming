package com.devok.common.models;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CharacterKeys implements Serializable {
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "SERVER_ID")
    private String serverId;

    public CharacterKeys(String userId, String serverId) {
        this.userId = userId;
        this.serverId = serverId;
    }

    public CharacterKeys() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }
}
