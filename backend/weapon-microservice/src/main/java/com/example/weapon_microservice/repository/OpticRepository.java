package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.optic.OpticModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OpticRepository extends MongoRepository<OpticModel, String> {

    Optional<OpticModel> readByReference(String reference);
}
