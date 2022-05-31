package com.devok.games.geoguessr.services.images;

import com.devok.common.database.EMRepository;
import com.devok.games.geoguessr.api.geonames.GeoNames;
import com.devok.games.geoguessr.api.geonames.model.GeoData;
import com.devok.games.geoguessr.api.mapillary.MapillaryService;
import com.devok.games.geoguessr.api.mapillary.authentication.AuthenticationService;
import com.devok.games.geoguessr.api.mapillary.image.model.ImageDTO;
import com.devok.games.geoguessr.api.mapillary.image.model.ImageListDTO;
import com.devok.games.geoguessr.api.mapillary.image.model.mapper.ImageMapper;
import com.devok.games.geoguessr.services.images.model.Image;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Startup
@Singleton
public class LoadImagesJob extends EMRepository<Image> {

    private final static String JOB_NAME = "Load_Images_Job";

    @Inject
    private MapillaryService mapillaryService;

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private ImageMapper imageMapper;

    @Inject
    private ImageFacade imageFacade;

    @Resource
    private TimerService timerService;

    @Inject
    private GeoNames geoNames;

    @PostConstruct
    private void init() {
        timerService.createIntervalTimer(0, 10000, new TimerConfig(JOB_NAME, false));
    }

    @Timeout
    public void timeout(Timer timer) {
        int numberOfImagesToLoad = getNumberOfImagesToLoad();
        if (numberOfImagesToLoad > 0) {
            loadImagesFromMapillary(numberOfImagesToLoad);
        }
    }

    public int getNumberOfImagesToLoad() {
        return 20 - createNamedQuery("Image.getLoadedImages", Image.class).getResultList().size();
    }

    @Transactional(dontRollbackOn = Exception.class)
    public void loadImagesFromMapillary(int numberOfImagesToLoad) {
        boolean trustedLocation;
        int attempts;
        for (int i = 0; i < numberOfImagesToLoad; i++) {
            trustedLocation = false;
            attempts = 0;
            ImageListDTO imageListDTO = new ImageListDTO();
            do {
                try {
                    imageListDTO = mapillaryService.getImages(authenticationService.getAccessToken(),
                            "id,geometry,thumb_original_url",
                            getRandomCoordinates(),
                            "20");
                    System.out.println("Number of photos: " + imageListDTO.getData().size());
                    trustedLocation = imageListDTO.getData().size() >= 20;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    attempts++;
                    if (attempts == 5) {
                        return;
                    }
                }
            }
            while (!trustedLocation);

            ImageDTO imageDTO = imageListDTO.getData().get(0);
            String address = imageFacade.getAddressFromCoordinates(imageDTO.getGeometry().getCoordinates());
            persist(imageMapper.mapToImage(imageDTO, address));
        }
    }

    private String getRandomCoordinates() {
        GeoData randomLocation = geoNames.getAddressFromCoordinates();
        float minX = randomLocation.getNearest().getLatitude();
        float maxX = minX + 2f;
        float minY = randomLocation.getNearest().getLongitude();
        float maxY = minY + 2f;

        String coordinates = minX + "," + minY + "," + maxX + "," + maxY;
        System.out.println("Coordinates: " + coordinates);
        return coordinates;
    }
}
