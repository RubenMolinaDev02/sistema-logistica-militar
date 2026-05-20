package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.common.enums.StockAtachmentSystem;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.model.weapon.enums.ActionType;
import com.example.weapon_microservice.model.weapon.enums.FireMode;
import com.example.weapon_microservice.model.weapon.enums.WeaponType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface WeaponRepository extends MongoRepository<WeaponModel, String>{
    @Override
    List<WeaponModel> findAllById(Iterable<String> strings);

    Optional<WeaponModel> readByReference(String reference);
}
