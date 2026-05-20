package com.example.inventory_microservice.model.stock.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class StockRequest {
    @NotBlank
    private String skuId;
    @NotBlank
    private String locationId;
    @PositiveOrZero
    private int units;
}
