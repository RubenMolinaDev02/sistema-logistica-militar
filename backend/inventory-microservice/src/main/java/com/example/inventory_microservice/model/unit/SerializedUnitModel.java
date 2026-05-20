package com.example.inventory_microservice.model.unit;

import com.example.inventory_microservice.model.unit.enums.LogisticalStatus;
import com.example.inventory_microservice.model.unit.enums.UnitStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "serializedUnit")
@Builder
@Data
public class SerializedUnitModel {
    @Id
    private String id;
    private String skuId;
    private String locationId;
    private String serialNumber;
    private UnitStatus status;
    private LogisticalStatus logisticalStatus;
}
