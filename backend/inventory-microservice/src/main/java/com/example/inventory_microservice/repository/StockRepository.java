package com.example.inventory_microservice.repository;

import com.example.inventory_microservice.model.stock.StockModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends MongoRepository<StockModel, String> {
    List<StockModel> findAllBySkuId(String skuId);

    List<StockModel> findAllByLocationId(String locationId);

    Optional<StockModel> findByReference(String reference);

    boolean existsByReference(String reference);

    boolean existsBySkuIdAndLocationId(String skuId, String locationId);

    Optional<StockModel> findBySkuIdAndLocationId(String skuId, String locationId);
}
