package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.manufacturer.WeaponManufacturerModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeaponManufacturerRepository  extends MongoRepository<WeaponManufacturerModel, String> {
    WeaponManufacturerModel readByReference(String reference);
}
