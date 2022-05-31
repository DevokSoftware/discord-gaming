package com.devok.games.geoguessr.services.images;

public class PointSystem {

    public static int getPoints(double distance) {
        //TODO - implement a correct point system

        if (distance < 400) {
            return 5000;
        } else if (distance < 1000) {
            return 4500;
        } else if (distance < 2000) {
            return 3500;
        } else if (distance < 3000) {
            return 2500;
        } else if (distance < 4000) {
            return 1500;
        } else if (distance < 5000) {
            return 500;
        }
        return 1;
    }
}
