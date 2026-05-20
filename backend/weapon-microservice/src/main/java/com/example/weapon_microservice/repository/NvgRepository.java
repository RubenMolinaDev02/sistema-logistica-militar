package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.nvg.NvgModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NvgRepository extends MongoRepository<NvgModel, String> {
    Optional<NvgModel> readByReference(String reference);
}
