package com.example.inventory_microservice.repository;

import com.example.inventory_microservice.model.unit.SerializedUnitModel;
import com.example.inventory_microservice.model.unit.enums.LogisticalStatus;
import com.example.inventory_microservice.model.unit.enums.UnitStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UnitRepository extends MongoRepository<SerializedUnitModel, String> {
    boolean existsBySerialNumber(String serialNumber);

    Optional<SerializedUnitModel> findBySerialNumber(String serialNumber);

    List<SerializedUnitModel> findAllBySkuId(String skuId);

    List<SerializedUnitModel> findAllBySkuIdAndLocationId(String skuId, String locationId);


    List<SerializedUnitModel> findAllBySerialNumberIn(List<String> serialNumbers);

    List<SerializedUnitModel> findAllBySkuIdAndLocationIdAndStatusInAndLogisticalStatusIn(String skuId, String locationId, Collection<UnitStatus> statuses, Collection<LogisticalStatus> logisticalStatuses);
}
