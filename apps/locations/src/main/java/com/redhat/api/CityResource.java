package com.redhat.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import java.util.List;

import com.redhat.domain.model.Location;
import com.redhat.domain.repository.LocationRepository;

@Path("/api/v1/cities")
public class CityResource {
    @Inject
    LocationRepository repository;

    @GET
    @Path("/states")
    public List<Location> getStates(@QueryParam("user_key") String userKey) {
        return repository.findAllStates();
    }

    @GET
    @Path("/regions")
    public List<Location> getRegions(@QueryParam("user_key") String userKey) {
        return repository.findAllRegions();
    }
}