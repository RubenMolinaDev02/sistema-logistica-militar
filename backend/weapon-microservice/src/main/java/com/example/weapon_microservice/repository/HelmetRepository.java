package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.helmet.HelmetModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HelmetRepository  extends MongoRepository<HelmetModel, String> {
    Optional<HelmetModel> readByReference(String reference);
}
