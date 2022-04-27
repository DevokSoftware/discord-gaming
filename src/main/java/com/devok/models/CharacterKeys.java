package com.devok.models;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CharacterKeys implements Serializable {
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "CHANNEL_ID")
    private String channelId;

    public CharacterKeys(String userId, String channelId) {
        this.userId = userId;
        this.channelId = channelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
