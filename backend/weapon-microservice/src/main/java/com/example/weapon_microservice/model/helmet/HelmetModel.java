package com.example.weapon_microservice.model.helmet;

import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.example.weapon_microservice.model.helmet.enums.HelmetLevel;
import com.example.weapon_microservice.model.helmet.enums.HelmetMaterial;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "helmet")
@Builder
@Getter
@Setter
public class HelmetModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private HelmetLevel level;
    private HelmetMaterial material;
    private double weight;
    private ServiceStatus status;
}
