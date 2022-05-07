package com.devok.games.geoguessr.api;

import com.devok.games.geoguessr.api.model.ImageList;
import com.devok.games.geoguessr.api.model.TokenRequest;
import com.devok.games.geoguessr.api.model.TokenResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RegisterRestClient(baseUri = "https://graph.mapillary.com")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface MapillaryService {

    @GET
    Response connectToMapillary(@QueryParam("clientId") String clientId);

    @POST
    @Path("/token")
    TokenResponse getAccessToken(@HeaderParam("Authorization") String headerAuth, TokenRequest tokenRequest);

    @POST
    @Path("/token")
    Response getAccessToken2(@HeaderParam("Authorization") String headerAuth, TokenRequest tokenRequest);

    @GET
    @Path("/images")
    ImageList getImages(@QueryParam("access_token") String accessToken, @QueryParam("fields") String fields, @QueryParam("bbox") String bbox);
}
