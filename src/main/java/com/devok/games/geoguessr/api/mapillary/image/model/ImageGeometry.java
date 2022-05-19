package com.devok.games.geoguessr.api.mapillary.image.model;

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;
import java.util.Arrays;

public class ImageGeometry implements Serializable {
    @JsonbProperty("type")
    private String type;

    @JsonbProperty("coordinates")
    private double[] coordinates;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "ImageGeometry{" +
                "type='" + type + '\'' +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }
}
