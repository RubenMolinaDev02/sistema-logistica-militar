package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.magazine.MagazineModel;
import com.example.weapon_microservice.model.miscItems.MiscItemModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MiscItemRepository extends MongoRepository<MiscItemModel, String> {
    Optional<MiscItemModel> readByReference(String reference);
}
