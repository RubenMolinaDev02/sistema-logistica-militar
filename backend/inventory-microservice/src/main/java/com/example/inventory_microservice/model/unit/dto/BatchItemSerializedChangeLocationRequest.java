package com.example.inventory_microservice.model.unit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
@Data
public class BatchItemSerializedChangeLocationRequest {
    @NotEmpty
    private List<String> unitIds;
    @NotBlank
    private String destinationReference;
}
