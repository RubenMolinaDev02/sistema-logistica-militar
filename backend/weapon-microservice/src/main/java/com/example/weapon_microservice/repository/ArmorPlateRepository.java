package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.armorPlate.ArmorPlateModel;
import com.example.weapon_microservice.model.armorVest.ArmorVestModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ArmorPlateRepository extends MongoRepository<ArmorPlateModel, String> {
    Optional<ArmorPlateModel> readByReference(String reference);
}
