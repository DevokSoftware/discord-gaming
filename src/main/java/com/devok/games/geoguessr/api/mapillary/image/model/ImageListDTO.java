package com.devok.games.geoguessr.api.mapillary.image.model;

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;
import java.util.List;


public class ImageListDTO implements Serializable {

    @JsonbProperty("data")
    private List<ImageDTO> imageDTOS;

    public List<ImageDTO> getImages() {
        return imageDTOS;
    }

    public void setImages(List<ImageDTO> imageDTOS) {
        this.imageDTOS = imageDTOS;
    }
}
