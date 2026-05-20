package com.example.weapon_microservice.model.ammo;

import com.example.weapon_microservice.model.ammo.enums.AmmoType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "ammo")
@Builder
@Data
public class AmmoModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String caliberId;
    private AmmoType type;
}
