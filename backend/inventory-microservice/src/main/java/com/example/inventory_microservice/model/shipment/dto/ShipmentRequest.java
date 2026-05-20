package com.example.inventory_microservice.model.shipment.dto;

import com.example.inventory_microservice.model.batch.batchModel.dto.BatchRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ShipmentRequest {
    @NotBlank
    private String supplierId;
    @NotBlank
    private String destinyId;
    @NotNull
    @Valid
    private List<BatchRequest> batches;
}
