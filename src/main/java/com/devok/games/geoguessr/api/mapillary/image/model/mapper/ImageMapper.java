package com.devok.games.geoguessr.api.mapillary.image.model.mapper;

import com.devok.games.geoguessr.api.mapillary.image.model.ImageDTO;
import com.devok.games.geoguessr.services.images.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ImageMapper {

    @Mapping(source = "imageDTO.imageUrl", target = "url")
    @Mapping(expression = "java(setLatitude(imageDTO.getGeometry().getCoordinates()))", target = "latitude")
    @Mapping(expression = "java(setLongitude(imageDTO.getGeometry().getCoordinates()))", target = "longitude")
    @Mapping(constant = "false", target = "used")
    @Mapping(source = "address", target = "address")
    Image mapToImage(ImageDTO imageDTO, String address);

    default Double setLatitude(double[] coordinates) {
        if(coordinates != null){
            return coordinates[0];
        }
        return null;
    }

    default Double setLongitude(double[] coordinates) {
        if(coordinates != null){
            return coordinates[1];
        }
        return null;
    }
}
