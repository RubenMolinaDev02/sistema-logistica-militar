package com.example.inventory_microservice.model.batch.batchModel;

import com.example.inventory_microservice.model.batch.batchItemGeneric.BatchItemGenericModel;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(value = "batch")
@Data
@Builder
public class BatchModel {
    @Id
    private String id;
    private String shipmentId;
    private List<BatchItemGenericModel> genericItems;
    private List<String> serializedItemsIds;
    private String reference;
}