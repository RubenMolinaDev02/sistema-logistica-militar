package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.holster.HolsterModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HolsterRepository extends MongoRepository<HolsterModel, String> {
    Optional<HolsterModel> readByReference(String reference);
}
