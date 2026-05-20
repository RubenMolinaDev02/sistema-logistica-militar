package com.example.unit_microservice.model.unit;

import com.example.unit_microservice.model.unit.enums.UnitType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("units")
@Data
@Builder
public class UnitModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private UnitType type;
    private String parentUnitId;
}
