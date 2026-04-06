package com.redhat.infrastructure.client;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "https://servicodados.ibge.gov.br/api/v1/localidades")
@Produces(MediaType.APPLICATION_JSON)
public interface IBGEClient {
    @GET
    @Path("/estados")
    List<IBGERecord> getStates();

    @GET
    @Path("/regioes")
    List<IBGERecord> getRegions();
}
