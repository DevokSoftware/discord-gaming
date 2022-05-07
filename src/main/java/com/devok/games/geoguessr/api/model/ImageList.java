package com.devok.games.geoguessr.api.model;

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;
import java.util.List;


public class ImageList implements Serializable {

    @JsonbProperty("data")
    private List<Image> images;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
