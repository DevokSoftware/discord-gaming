package com.devok.games.geoguessr.api.geonames;


import com.devok.games.geoguessr.api.geonames.model.GeoData;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "https://api.3geonames.org/?randomland=yes&json=1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface GeoNames {

    @GET
    GeoData getAddressFromCoordinates();
}