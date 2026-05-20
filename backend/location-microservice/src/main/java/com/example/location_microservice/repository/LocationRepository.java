package com.example.location_microservice.repository;

import com.example.location_microservice.model.location.LocationModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LocationRepository extends MongoRepository<LocationModel, String> {
    Optional<LocationModel> readByReference(String reference);

    boolean existsByReference(String reference);
}
