package com.example.inventory_microservice.model.batch.batchModel.dto;

import com.example.inventory_microservice.model.batch.batchItemGeneric.BatchItemGenericModel;
import lombok.Data;

import java.util.List;
@Data
public class BatchResponse {
    private String id;
    private String shipmentId;
    private List<BatchItemGenericModel> genericItems;
    private List<String> serializedItemsIds;
    private String reference;
}
