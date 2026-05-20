package com.example.inventory_microservice.model.sku.dto;

import com.example.inventory_microservice.model.stock.dto.StockResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class SkuResponse {
    private String id;
    private String itemId;
    private String reference;
    private Map<String, Object> attributes;
    private boolean serialized;
    private boolean active;
}
