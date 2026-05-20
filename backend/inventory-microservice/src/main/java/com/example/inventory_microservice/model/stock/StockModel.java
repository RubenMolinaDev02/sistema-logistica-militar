package com.example.inventory_microservice.model.stock;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "stock")
@Data
@Builder
public class StockModel {
    @Id
    private String id;
    private String reference;
    private String skuId;
    private String locationId;
    private int units;
}