package com.devok.games.geoguessr.callback;

import com.devok.games.geoguessr.api.common.AuthenticationService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


@Path("/mapillary/callback")
public class MapillaryCallback {

    @Inject
    private AuthenticationService authenticationService;

    @GET
    public Response callback(@QueryParam("code") String code) {
        authenticationService.setMapillaryCode(code);
        return Response.accepted().build();
    }
}