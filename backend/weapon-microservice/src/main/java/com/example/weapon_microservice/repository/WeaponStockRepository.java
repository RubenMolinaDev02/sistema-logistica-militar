package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.stock.WeaponStockModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WeaponStockRepository  extends MongoRepository<WeaponStockModel, String> {
    Optional<WeaponStockModel> readByReference(String reference);
}
