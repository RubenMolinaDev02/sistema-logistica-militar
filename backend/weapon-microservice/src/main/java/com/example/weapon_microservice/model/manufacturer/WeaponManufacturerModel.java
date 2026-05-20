package com.example.weapon_microservice.model.manufacturer;

import com.example.weapon_microservice.model.manufacturer.dto.ManufacturerRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "weapon-manufacturer")
@Builder
@Getter
@Setter
public class WeaponManufacturerModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String country;
    private String logo;
}
