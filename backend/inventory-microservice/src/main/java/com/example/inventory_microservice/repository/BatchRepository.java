package com.example.inventory_microservice.repository;

import com.example.inventory_microservice.model.batch.batchModel.BatchModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BatchRepository extends MongoRepository<BatchModel, String> {
    List<BatchModel> findAllByShipmentId(String shipmentId);

    Optional<BatchModel> findByReference(String reference);
}
