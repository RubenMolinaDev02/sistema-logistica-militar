package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.barrelAtachment.BarrelAtachmentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BarrelAtachmentRepository  extends MongoRepository<BarrelAtachmentModel, String> {
    Optional<BarrelAtachmentModel> readByReference(String reference);
}
