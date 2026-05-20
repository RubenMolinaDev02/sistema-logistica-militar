package com.example.weapon_microservice.model.bayonet;

import java.util.List;

import com.example.weapon_microservice.model.bayonet.enums.BayonetType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "bayonet")
@Getter
@Builder
@Setter
public class BayonetModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private double bladeLength;
    private BayonetType type;
    private List<String> compatibleWeaponsIds;
}

