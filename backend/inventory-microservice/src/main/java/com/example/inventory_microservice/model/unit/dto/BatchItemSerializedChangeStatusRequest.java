package com.example.inventory_microservice.model.unit.dto;

import com.example.inventory_microservice.model.unit.enums.UnitStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class BatchItemSerializedChangeStatusRequest {
    @NotEmpty
    private List<String> unitIds;
    @NotNull
    private UnitStatus status;
}
