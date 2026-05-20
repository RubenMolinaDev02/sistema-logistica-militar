package com.example.weapon_microservice.model.gasMask;

import com.example.weapon_microservice.model.common.enums.GasMaskStandar;
import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "gasMask")
@Data
@Builder
public class GasMaskModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private GasMaskStandar standard;
    private ServiceStatus status;
}
