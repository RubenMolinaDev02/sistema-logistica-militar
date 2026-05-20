package com.example.inventory_microservice.model.shipment.dto;

import com.example.inventory_microservice.model.shipment.enums.ShipmentStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ShipmentUpdateRequest {
    private String reference;
    private String supplierLocationId;
    private String destinyLocationId;
    private ShipmentStatus status;
    private LocalDate creationDate;
    private LocalDate arrivalDate;
}
