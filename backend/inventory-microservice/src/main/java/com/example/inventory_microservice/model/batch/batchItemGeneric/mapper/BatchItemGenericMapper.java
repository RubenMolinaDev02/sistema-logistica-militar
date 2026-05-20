package com.example.inventory_microservice.model.batch.batchItemGeneric.mapper;

import com.example.inventory_microservice.model.batch.batchItemGeneric.BatchItemGenericModel;
import com.example.inventory_microservice.model.batch.batchItemGeneric.dto.BatchItemGenericRequest;

public class BatchItemGenericMapper {
    public static BatchItemGenericModel modelFromRequest(BatchItemGenericRequest request, String skuId){
        return BatchItemGenericModel.builder()
                .skuId(skuId)
                .quantity(request.getQuantity())
                .build();
    }
}
