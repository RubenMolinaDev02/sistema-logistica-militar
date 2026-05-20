package com.example.unit_microservice.repository;

import com.example.unit_microservice.model.unit.UnitModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UnitRepository extends MongoRepository<UnitModel, String> {
    Optional<UnitModel> readByReference(String reference);

    boolean existsByReference(String reference);

    List<UnitModel> findAllByParentUnitId(String parentUnitId);
}
