package com.devok.games.geoguessr.api;


import com.devok.games.geoguessr.api.model.positionstack.AddressList;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@RegisterRestClient(baseUri = "http://api.positionstack.com/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface PositionStack {

    @GET
    @Path("/reverse")
    AddressList getAddressFromCoordinates(@QueryParam("access_key") String accessKey, @QueryParam("query") String query);
}
