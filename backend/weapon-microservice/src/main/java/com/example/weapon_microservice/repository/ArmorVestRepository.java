package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.ammo.AmmoModel;
import com.example.weapon_microservice.model.armorVest.ArmorVestModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ArmorVestRepository extends MongoRepository<ArmorVestModel, String> {
    Optional<ArmorVestModel> readByReference(String reference);
}
