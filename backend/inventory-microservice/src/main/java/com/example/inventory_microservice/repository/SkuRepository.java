package com.example.inventory_microservice.repository;

import com.example.inventory_microservice.model.sku.SkuModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SkuRepository extends MongoRepository<SkuModel, String> {
    Optional<SkuModel> readByReference(String reference);

    boolean existsByReference(String reference);

    List<SkuModel> readAllByItemId(String itemId);

    List<SkuModel> readAllByActive(boolean active);

    List<SkuModel> readAllBySerialized(boolean serialized);

    List<SkuModel> findAllByAttributesContains(Map<String, Object> attributes);

    List<SkuModel> findAllByAttributesContainsAndItemId(Map<String, Object> attributes, String itemId);

    List<SkuModel> findAllByItemId(String itemId);
}
