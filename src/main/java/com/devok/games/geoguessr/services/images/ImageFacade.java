package com.devok.games.geoguessr.services.images;

import com.devok.common.database.EMRepository;
import com.devok.games.geoguessr.services.images.model.Image;

import javax.transaction.Transactional;
import java.util.List;

public class ImageFacade extends EMRepository<Image> {

    @Transactional
    public Image getRandomImage(String userId, String channelId) {
        List<Image> imageList = getUnusedImages();
        if (!imageList.isEmpty()) {
            Image image = imageList.get(0);
        }
        return null;
    }


    private List<Image> getUnusedImages() {
        return createNamedQuery("Image.getLoadedImages", Image.class).getResultList();
    }
}
