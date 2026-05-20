package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.gasMaskFilter.GasMaskFilterModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GasMaskFilterRepository  extends MongoRepository<GasMaskFilterModel, String> {
    Optional<GasMaskFilterModel> readByReference(String reference);
}
