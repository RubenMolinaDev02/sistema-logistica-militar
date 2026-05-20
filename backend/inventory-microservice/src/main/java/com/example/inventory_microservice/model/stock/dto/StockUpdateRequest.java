package com.example.inventory_microservice.model.stock.dto;

import lombok.Data;

@Data
public class StockUpdateRequest {
    private String reference;
    private Integer units;
}
