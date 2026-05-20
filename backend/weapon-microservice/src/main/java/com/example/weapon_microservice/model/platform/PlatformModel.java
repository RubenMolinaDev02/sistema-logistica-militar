package com.example.weapon_microservice.model.platform;
import com.example.weapon_microservice.model.platform.dto.PlatformRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "platform")
@Builder
@Getter
@Setter
public class PlatformModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private String description;
}
