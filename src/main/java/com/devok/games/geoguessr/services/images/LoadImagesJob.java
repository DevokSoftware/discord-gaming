package com.devok.games.geoguessr.services.images;

import com.devok.common.database.EMRepository;
import com.devok.games.geoguessr.api.MapillaryService;
import com.devok.games.geoguessr.api.mapillary.authentication.AuthenticationService;
import com.devok.games.geoguessr.api.mapillary.image.model.ImageDTO;
import com.devok.games.geoguessr.api.mapillary.image.model.ImageListDTO;
import com.devok.games.geoguessr.api.mapillary.image.model.mapper.ImageMapper;
import com.devok.games.geoguessr.services.images.model.Image;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.Random;


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

    @Resource
    private TimerService timerService;

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

    public void loadImagesFromMapillary(int numberOfImagesToLoad) {
        for (int i = 0; i < numberOfImagesToLoad; i++) {
            ImageListDTO imageListDTO;
            do {
                imageListDTO = mapillaryService.getImages(authenticationService.getAccessToken(),
                        "id,geometry,thumb_original_url",
                        getRandomCoordinates(),
                        "20");
            }
            while (imageListDTO.getImages().size() < 20);

            ImageDTO imageDTO = imageListDTO.getImages().get(0);
            persist(imageMapper.mapToImage(imageDTO));
        }
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
