package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.gasMask.GasMaskModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GasMaskRepository  extends MongoRepository<GasMaskModel, String> {
    Optional<GasMaskModel> readByReference(String reference);
}
