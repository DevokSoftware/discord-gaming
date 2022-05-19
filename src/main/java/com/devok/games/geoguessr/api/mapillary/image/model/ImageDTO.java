package com.devok.games.geoguessr.api.mapillary.image.model;

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;

public class ImageDTO implements Serializable {

    @JsonbProperty("id")
    private String imageId;

    @JsonbProperty("geometry")
    private ImageGeometry geometry;

    @JsonbProperty("thumb_original_url")
    private String imageUrl;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public ImageGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(ImageGeometry geometry) {
        this.geometry = geometry;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId='" + imageId + '\'' +
                ", geometry=" + geometry +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
