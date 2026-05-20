package com.example.inventory_microservice.repository;

import com.example.inventory_microservice.model.shipment.ShipmentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShipmentRepository extends MongoRepository<ShipmentModel, String> {
    Optional<ShipmentModel> findByReference(String reference);
}
