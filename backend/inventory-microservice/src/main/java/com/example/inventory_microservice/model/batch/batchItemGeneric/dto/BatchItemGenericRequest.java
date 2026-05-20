package com.example.inventory_microservice.model.batch.batchItemGeneric.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BatchItemGenericRequest {
    @NotBlank
    private String skuReference;
    @Positive
    private int quantity;
}
