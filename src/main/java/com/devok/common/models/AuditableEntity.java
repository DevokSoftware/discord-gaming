package com.devok.common.models;


import javax.persistence.*;
import java.time.OffsetDateTime;

@Embeddable
@MappedSuperclass
public class AuditableEntity {

    @Column(name = "CREATED_AT")
    private OffsetDateTime createdAt;

    @Column(name = "LAST_UPDATE")
    private OffsetDateTime lastUpdate;

    @PrePersist
    public void onPersist(){
        createdAt = OffsetDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        lastUpdate = OffsetDateTime.now();
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(OffsetDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
