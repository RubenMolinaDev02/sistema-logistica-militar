package com.example.inventory_microservice.service.searchFilterDto;

import com.example.inventory_microservice.model.shipment.enums.ShipmentStatus;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ShipmentSearchFilter {

    private String supplierLocationId;
    private String destinyLocationId;
    private ShipmentStatus status;

    private LocalDate creationDate;
    private LocalDate arrivalDate;

    private Integer page = 0;
    private Integer size = 20;

    private String sortBy = "creationDate";
    private String sortDirection = "DESC";
}
