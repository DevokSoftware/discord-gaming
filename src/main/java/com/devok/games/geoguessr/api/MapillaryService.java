package com.devok.games.geoguessr.api;

import com.devok.games.geoguessr.api.model.TokenRequest;
import com.devok.games.geoguessr.api.model.TokenResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RegisterRestClient(baseUri = "https://graph.mapillary.com/token")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface MapillaryService {

    @GET
    Response connectToMapillary(@QueryParam("clientId") String clientId);

    @POST
    TokenResponse getAccessToken(@HeaderParam("Authorization") String headerAuth, TokenRequest tokenRequest);
}
