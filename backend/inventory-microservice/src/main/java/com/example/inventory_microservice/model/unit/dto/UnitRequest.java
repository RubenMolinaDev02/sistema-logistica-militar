package com.example.inventory_microservice.model.unit.dto;

import com.example.inventory_microservice.model.unit.enums.UnitStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class UnitRequest {
    @NotBlank
    private String skuId;
    @NotBlank
    private String locationId;
    @NotEmpty
    private List<String> serialNumbers;
    @NotNull
    private UnitStatus status;
}
