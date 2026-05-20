package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.ammo.AmmoModel;
import com.example.weapon_microservice.model.caliber.CaliberModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AmmoRepository extends MongoRepository<AmmoModel, String> {
    Optional<AmmoModel> readByReference(String reference);
}
