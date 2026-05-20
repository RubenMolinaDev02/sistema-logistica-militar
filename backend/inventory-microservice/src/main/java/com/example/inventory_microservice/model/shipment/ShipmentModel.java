package com.example.inventory_microservice.model.shipment;

import com.example.inventory_microservice.model.shipment.enums.ShipmentStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Document(value = "shipment")
@Builder
@Data
public class ShipmentModel {
    @Id
    private String id;
    private String reference;
    private String supplierLocationId;
    private String destinyLocationId;
    private ShipmentStatus status;
    private LocalDate creationDate;
    private LocalDate arrivalDate;
}
