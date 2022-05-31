package com.devok.games.geoguessr.api.positionstack;


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

    @GET
    @Path("/forward")
    AddressList getCoordinatesFromAddress(@QueryParam("access_key") String accessKey, @QueryParam("query") String query);

}
