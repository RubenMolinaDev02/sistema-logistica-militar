package com.example.inventory_microservice.model.sku.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public class SkuRequest {
    @NotBlank
    private String itemId;
    @NotNull
    private Map<String, Object> attributes;
    @NotNull
    private Boolean active;
    @NotNull
    private Boolean serialized;
}
