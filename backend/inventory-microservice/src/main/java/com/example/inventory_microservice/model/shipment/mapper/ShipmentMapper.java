package com.example.inventory_microservice.model.shipment.mapper;

import com.example.inventory_microservice.client.LocationResponse;
import com.example.inventory_microservice.model.shipment.ShipmentModel;
import com.example.inventory_microservice.model.shipment.dto.ShipmentResponse;
import com.example.inventory_microservice.model.shipment.enums.ShipmentStatus;

import java.time.LocalDate;
import java.util.List;

public class ShipmentMapper {
    public static ShipmentModel modelFromRequest(
            String id,
            String reference,
            String supplierLocationId,
            String destinyLocationId
    ) {
        return ShipmentModel.builder()
                .id(id)
                .reference(reference)
                .supplierLocationId(supplierLocationId)
                .destinyLocationId(destinyLocationId)
                .status(ShipmentStatus.CREATED)
                .creationDate(LocalDate.now())
                .build();
    }

    public static ShipmentResponse responseFromModel(ShipmentModel model, LocationResponse destiny, LocationResponse supplier){
        return ShipmentResponse.builder()
                .id(model.getId())
                .reference(model.getReference())
                .supplierLocation(supplier)
                .destinyLocation(destiny)
                .status(model.getStatus())
                .creationDate(model.getCreationDate())
                .arrivalDate(model.getArrivalDate())
                .build();
    }
}
