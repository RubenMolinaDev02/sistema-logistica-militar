package com.example.inventory_microservice.model.batch.batchModel.mapper;

import com.example.inventory_microservice.model.batch.batchItemGeneric.BatchItemGenericModel;
import com.example.inventory_microservice.model.batch.batchModel.BatchModel;
import com.example.inventory_microservice.model.batch.batchModel.dto.BatchRequest;

import java.util.List;

public class BatchMapper {
    public static BatchModel modelFromRequest(
            String id,
            String reference,
            String shipmentId,
            List<BatchItemGenericModel> genericItems,
            List<String> serializedItemsIds) {
        return BatchModel.builder()
                .id(id)
                .shipmentId(shipmentId)
                .genericItems(genericItems)
                .serializedItemsIds(serializedItemsIds)
                .reference(reference)
                .build();
    }
}
