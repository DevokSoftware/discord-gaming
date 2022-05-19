package com.devok.games.geoguessr.api.mapillary.image;

import com.devok.games.geoguessr.api.PositionStack;
import com.devok.games.geoguessr.api.positionstack.AddressList;

import javax.inject.Inject;

public class ImageService {

    @Inject
    private PositionStack positionStack;

    public String getAddressCoordinates(double[] coordinates) {
        String query = coordinates[0] + ", " + coordinates[1];
        System.out.println("Getting address for: " + query);
        AddressList addressList = positionStack.getAddressFromCoordinates(System.getProperty("positionStack.accessKey"), query);
        if (addressList != null && !addressList.getAddresses().isEmpty()) {
            return addressList.getAddresses().get(0).getLabel();
        }
        return "N/A";
    }
}
