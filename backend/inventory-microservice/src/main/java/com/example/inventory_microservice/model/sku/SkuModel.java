package com.example.inventory_microservice.model.sku;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(value = "sku")
@Builder
@Data
public class SkuModel {
    @Id
    private String id;
    private String itemId;
    private String reference;
    private Map<String, Object> attributes;
    private boolean active;
    private boolean serialized;
}
