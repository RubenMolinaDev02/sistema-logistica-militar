package com.example.weapon_microservice.model.caliber;
import com.example.weapon_microservice.model.caliber.dto.CaliberRequest;
import com.example.weapon_microservice.model.caliber.enums.CaliberStandard;
import com.example.weapon_microservice.model.caliber.enums.CaliberType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "caliber")
@Builder
@Getter
@Setter
public class CaliberModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private CaliberType type;
    private CaliberStandard standard;
}
