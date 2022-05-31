package com.devok.games.geoguessr.api.mapillary;

import com.devok.games.geoguessr.api.mapillary.authentication.model.TokenRequest;
import com.devok.games.geoguessr.api.mapillary.authentication.model.TokenResponse;
import com.devok.games.geoguessr.api.mapillary.image.model.ImageListDTO;
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

    @GET
    @Path("/images")
    ImageListDTO  getImages(@QueryParam("access_token") String accessToken, @QueryParam("fields") String fields, @QueryParam("bbox") String bbox, @QueryParam("limit") String limit);
}
