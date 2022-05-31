package com.devok.games.geoguessr.services.images;

import com.devok.common.database.EMRepository;
import com.devok.games.geoguessr.api.positionstack.AddressList;
import com.devok.games.geoguessr.api.positionstack.PositionStack;
import com.devok.games.geoguessr.services.images.model.Image;
import org.apache.lucene.util.SloppyMath;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

public class ImageFacade extends EMRepository<Image> {
    @Inject
    private PositionStack positionStack;

    @Transactional
    public Image startGame(String userId, String serverId) {
        Image image = getActiveGame(userId, serverId);
        if (image != null) {
            return image;
        }
        return getRandomImage(userId, serverId);
    }

    @Transactional
    public Image getRandomImage(String userId, String serverId) {
        List<Image> imageList = getUnusedImages();
        if (!imageList.isEmpty()) {
            Image image = imageList.get(0);
            linkImageToUser(image, userId, serverId);
            return image;
        }
        return null;
    }

    public int calculateDistance(Image image, String address) {
        double[] coordinates = getCoordinatesFromAddress(address);
        if (coordinates == null) {
            throw new RuntimeException();
        }

        double distanceInMeters = SloppyMath.haversinMeters(image.getLatitude(), image.getLongitude(), coordinates[0], coordinates[1]);
        int distanceInKm = (int) distanceInMeters / 1000;
        System.out.println("Distance between points: " + distanceInKm + " km.");
        return distanceInKm;
    }




    public String getAddressFromCoordinates(double[] coordinates) {
        String query = coordinates[0] + ", " + coordinates[1];
        System.out.println("Getting address for coordinates: " + query);
        AddressList addressList = positionStack.getAddressFromCoordinates(System.getProperty("positionStack.accessKey"), query);
        if (addressList != null && !addressList.getAddresses().isEmpty()) {
            return addressList.getAddresses().get(0).getLabel();
        }
        return "N/A";
    }

    public double[] getCoordinatesFromAddress(String address) {
        System.out.println("Getting coordinates for address: " + address);
        AddressList addressList = positionStack.getCoordinatesFromAddress(System.getProperty("positionStack.accessKey"), address);
        if (addressList != null && !addressList.getAddresses().isEmpty()) {
            return new double[]{addressList.getAddresses().get(0).getLatitude(), addressList.getAddresses().get(0).getLongitude()};
        }
        return null;
    }

    public Image getActiveGame(String userId, String serverId) {
        try {
            return createNamedQuery("Image.getActiveGame", Image.class)
                    .setParameter("userId", userId)
                    .setParameter("serverId", serverId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void removeImage(String imageId) {
        Image image = find(Image.class, imageId);
        remove(image);
    }

    @Transactional
    private void linkImageToUser(Image image, String userId, String serverId) {
        image.setUsed(true);
        image.setUser(userId);
        image.setServer(serverId);
        merge(image);
    }

    private List<Image> getUnusedImages() {
        return createNamedQuery("Image.getLoadedImages", Image.class).getResultList();
    }
}
