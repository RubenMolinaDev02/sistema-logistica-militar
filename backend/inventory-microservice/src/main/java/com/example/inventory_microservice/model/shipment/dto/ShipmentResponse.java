package com.example.inventory_microservice.model.shipment.dto;

import com.example.inventory_microservice.client.LocationResponse;
import com.example.inventory_microservice.model.shipment.enums.ShipmentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ShipmentResponse {
    private String id;
    private String reference;
    private LocationResponse supplierLocation;
    private LocationResponse destinyLocation;
    private ShipmentStatus status;
    private LocalDate creationDate;
    private LocalDate arrivalDate;
}
