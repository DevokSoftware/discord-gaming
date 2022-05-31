package com.devok.games.geoguessr.api.mapillary.image.model;

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;
import java.util.List;


public class ImageListDTO implements Serializable {

    @JsonbProperty("data")
    private List<ImageDTO> data;

    public List<ImageDTO> getData() {
        return data;
    }

    public void setData(List<ImageDTO> data) {
        this.data = data;
    }
}
