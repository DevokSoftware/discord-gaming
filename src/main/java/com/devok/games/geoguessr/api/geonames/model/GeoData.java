package com.devok.games.geoguessr.api.geonames.model;


import javax.json.bind.annotation.JsonbProperty;

public class GeoData {

    @JsonbProperty("nearest")
    private Nearest nearest;

    public Nearest getNearest() {
        return nearest;
    }

    public void setNearest(Nearest nearest) {
        this.nearest = nearest;
    }

    public static class Nearest {
        @JsonbProperty("latt")
        private float latitude;

        @JsonbProperty("longt")
        private float longitude;

        @JsonbProperty("city")
        private String city;

        public float getLatitude() {
            return latitude;
        }

        public void setLatitude(float latitude) {
            this.latitude = latitude;
        }

        public float getLongitude() {
            return longitude;
        }

        public void setLongitude(float longitude) {
            this.longitude = longitude;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
