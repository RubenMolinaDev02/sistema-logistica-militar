package com.example.weapon_microservice.model.armorPlate;

import com.example.weapon_microservice.model.armorPlate.enums.PlateLevel;
import com.example.weapon_microservice.model.armorPlate.enums.PlateMaterial;
import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "plate")
@Builder
@Getter
@Setter
public class ArmorPlateModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private PlateLevel level;
    private PlateMaterial material;
    private double weight;
    private ServiceStatus status;
}
