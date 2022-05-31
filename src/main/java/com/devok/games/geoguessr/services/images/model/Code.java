package com.devok.games.geoguessr.services.images.model;

import com.devok.common.models.AuditableEntity;

import javax.persistence.*;

@Entity
@Table(name = "GEO_CODE", schema = "public")
@NamedQuery(name = "Token.getTokens", query = "select c from Code c")
public class Code extends AuditableEntity {
    @Id
    @Column(name = "MAPILLARY_CODE", length = 600)
    private String mapillaryCode;

    public Code() {
    }

    public Code(String mapillaryCode) {
        this.mapillaryCode = mapillaryCode;
    }

    public String getMapillaryCode() {
        return mapillaryCode;
    }

    public void setMapillaryCode(String mapillaryCode) {
        this.mapillaryCode = mapillaryCode;
    }
}

