package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.handguard.HandguardModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface HandguardRepository  extends MongoRepository<HandguardModel, String> {
    Optional<HandguardModel> readByReference(String reference);

    List<HandguardModel> findByCompatiblePlatformsIdsContains(String compatiblePlatformsIds);
}
