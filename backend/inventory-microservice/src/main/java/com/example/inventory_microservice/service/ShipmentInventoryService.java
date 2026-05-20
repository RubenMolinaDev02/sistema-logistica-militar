package com.example.inventory_microservice.service;

import com.example.inventory_microservice.client.LocationClient;
import com.example.inventory_microservice.model.batch.batchModel.dto.BatchRequest;
import com.example.inventory_microservice.model.unit.SerializedUnitModel;
import com.example.inventory_microservice.model.unit.enums.LogisticalStatus;
import com.example.inventory_microservice.model.unit.enums.UnitStatus;
import com.example.inventory_microservice.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ShipmentInventoryService {
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private LocationClient locationClient;
    @Autowired
    private UnitService unitService;

    public List<SerializedUnitModel> asignToDestinyMultiple(
            List<String> unitIds,
            String destinationId
    ) {
        locationClient.getById(destinationId);

        List<SerializedUnitModel> units = unitService.getAllById(unitIds);

        units.forEach(unit -> {
            if (unit.getLogisticalStatus() != LogisticalStatus.RESERVED)
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Only reserved items can be shiped"
                );

            if (unit.getStatus() == UnitStatus.DAMAGED || unit.getStatus() == UnitStatus.USABLE){
                unit.setLocationId(destinationId);
            }else throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Items can only be shiped if they are usable or damaged and not issued"
            );
        });

        return unitRepository.saveAll(units);
    }

    public List<String> reserveSerializedItems(
            BatchRequest batch
    ) {

        if (batch.getSerializedItems() == null
                || batch.getSerializedItems().isEmpty()) {
            return List.of();
        }

        List<String> serialNumbers = batch.getSerializedItems().stream()
                .flatMap(item -> item.getSerialNumbers().stream())
                .distinct()
                .toList();

        List<SerializedUnitModel> units =
                unitService.getAllBySerialNumbers(serialNumbers);

        unitService.validateAllSerialsExist(serialNumbers, units);

        return units.stream()
                .map(SerializedUnitModel::getId)
                .toList();
    }
}
