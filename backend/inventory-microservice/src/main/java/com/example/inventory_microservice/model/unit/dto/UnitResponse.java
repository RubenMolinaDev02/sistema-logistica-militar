package com.example.inventory_microservice.model.unit.dto;

import com.example.inventory_microservice.model.unit.enums.LogisticalStatus;
import com.example.inventory_microservice.model.unit.enums.UnitStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class UnitResponse {
    private String id;
    private String skuId;
    private String locationId;
    private String serialNumber;
    private UnitStatus status;
    private LogisticalStatus logisticalStatus;
}
