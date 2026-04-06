package com.redhat.domain.repository;

import java.util.List;

import com.redhat.domain.model.Location;

public interface LocationRepository {
    List<Location> findAllStates();
    List<Location> findAllRegions();
}
