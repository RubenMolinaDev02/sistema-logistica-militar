package com.example.inventory_microservice.model.batch.batchModel.dto;

import com.example.inventory_microservice.model.batch.batchItemGeneric.dto.BatchItemGenericRequest;
import com.example.inventory_microservice.model.unit.dto.BatchItemSerializedRequest;
import lombok.Data;

import java.util.List;
@Data
public class BatchRequest {
    private List<BatchItemGenericRequest> items;
    private List<BatchItemSerializedRequest> serializedItems;
}
