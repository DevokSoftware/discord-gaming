package com.devok.games.geoguessr.services.images.model;

import com.devok.common.models.AuditableEntity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "GEO_IMAGE", schema = "public")
@NamedQueries({
        @NamedQuery(name = "Image.getLoadedImages", query = "select i from Image i where i.used = false order by i.createdAt desc"),
        @NamedQuery(name = "Image.getActiveGame", query = "select i from Image i where i.used = true and i.user = :userId and i.server = :serverId")
})
public class Image extends AuditableEntity {
    @Id
    @Column(name = "IMAGE_ID")
    private String id;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "LONGITUDE")
    private double longitude;

    @Column(name = "URL", length = 1000)
    private String url;

    @Column(name = "USED")
    private boolean used;

    @Column(name = "USER_ID")
    private String user;

    @Column(name = "SERVER_ID")
    private String server;

    public Image() {
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}

