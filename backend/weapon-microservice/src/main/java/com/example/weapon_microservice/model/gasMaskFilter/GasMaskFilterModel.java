package com.example.weapon_microservice.model.gasMaskFilter;

import com.example.weapon_microservice.model.common.enums.GasMaskStandar;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "gasMaskFilter")
@Builder
@Data
public class GasMaskFilterModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private int useHours;
    private GasMaskStandar standard;
}
