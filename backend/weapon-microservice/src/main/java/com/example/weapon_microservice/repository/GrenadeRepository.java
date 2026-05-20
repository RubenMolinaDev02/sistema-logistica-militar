package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.grenade.GrenadeModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GrenadeRepository  extends MongoRepository<GrenadeModel, String> {
    Optional<GrenadeModel> readByReference(String reference);
}
