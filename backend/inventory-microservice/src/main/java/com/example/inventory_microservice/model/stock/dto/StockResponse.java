package com.example.inventory_microservice.model.stock.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class StockResponse {
    private String id;
    private String reference;
    private String skuId;
    private String locationId;
    private int units;
}
