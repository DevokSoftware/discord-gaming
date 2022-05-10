package com.devok.games.geoguessr.api.common;

import com.devok.games.geoguessr.api.MapillaryService;
import com.devok.games.geoguessr.api.PositionStack;
import com.devok.games.geoguessr.api.model.mapillary.ImageList;
import com.devok.games.geoguessr.api.model.positionstack.AddressList;

import javax.inject.Inject;
import java.util.Random;

public class ImageService {

    @Inject
    private MapillaryService mapillaryService;

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private PositionStack positionStack;

    public ImageList getRandomImage() {
        ImageList imageList;
        do {
            imageList = mapillaryService.getImages(authenticationService.getAccessToken(),
                    "id,geometry,thumb_original_url",
                    getRandomCoordinates(),
                    "20");
        }
        while (imageList.getImages().size() < 20);
        return imageList;
    }

    public String getAddressCoordinates(double[] coordinates) {
        String query = coordinates[0] + ", " + coordinates[1];
        System.out.println("Getting address for: " + query);
        AddressList addressList = positionStack.getAddressFromCoordinates(System.getProperty("positionStack.accessKey"), query);
        if (addressList != null && !addressList.getAddresses().isEmpty()) {
            return addressList.getAddresses().get(0).getLabel();
        }
        return "N/A";
    }

    private String getRandomCoordinates() {
        Random random = new Random();
        int incrementalX = random.nextInt(90) - 45;
        int incrementalY = random.nextInt(90) - 45;
        float minX = random.nextFloat() + incrementalX;
        float maxX = random.nextFloat() + minX + 0.1f;
        float minY = random.nextFloat() + incrementalY;
        float maxY = random.nextFloat() + minY + 0.1f;

        String coordinates = minX + "," + minY + "," + maxX + "," + maxY;
        System.out.println("Coordinates: " + coordinates);
        return coordinates;
    }
}
