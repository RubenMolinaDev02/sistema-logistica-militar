package com.example.inventory_microservice.model.batch.batchItemGeneric;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BatchItemGenericModel {
    private String skuId;
    private int quantity;
}
