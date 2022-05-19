package com.devok.games.geoguessr.services.games.model;

import com.devok.common.models.AuditableEntity;
import com.devok.games.geoguessr.services.images.model.Image;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "GEO_ACTIVE_GAME")
public class ActiveGame extends AuditableEntity {

    @Id
    @Column(name = "GAME_ID")
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "CHANNEL_ID")
    private String channelId;

    @OneToOne
    @JoinColumn(name = "IMAGE_ID")
    private Image image;

    @Override
    public void onPersist() {
        id = UUID.randomUUID().toString();
        super.onPersist();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
