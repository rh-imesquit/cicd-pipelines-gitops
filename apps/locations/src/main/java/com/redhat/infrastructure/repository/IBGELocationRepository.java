package com.redhat.infrastructure.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.redhat.domain.model.Location;
import com.redhat.domain.repository.LocationRepository;
import com.redhat.infrastructure.client.IBGEClient;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IBGELocationRepository implements LocationRepository {
    @RestClient
    IBGEClient client;

    @Override
    public List<Location> findAllStates() {
        return client.getStates().stream()
                .map(record -> new Location(record.nome(), record.sigla()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Location> findAllRegions() {
        return client.getRegions().stream()
                .map(record -> new Location(record.nome(), record.sigla()))
                .collect(Collectors.toList());
    }
}
