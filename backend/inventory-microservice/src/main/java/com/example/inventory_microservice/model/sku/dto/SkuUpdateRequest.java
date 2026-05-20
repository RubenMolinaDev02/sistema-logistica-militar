package com.example.inventory_microservice.model.sku.dto;

import lombok.Data;

import java.util.Map;
@Data
public class SkuUpdateRequest {
    private String reference;
    private Map<String, Object> attributes;
    private Boolean active;
}
