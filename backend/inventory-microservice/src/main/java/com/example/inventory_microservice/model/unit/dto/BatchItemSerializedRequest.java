package com.example.inventory_microservice.model.unit.dto;

import com.example.inventory_microservice.model.unit.enums.UnitStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class BatchItemSerializedRequest {
    @NotBlank
    private String skuReference;
    @NotEmpty
    private List<String> serialNumbers;
}
