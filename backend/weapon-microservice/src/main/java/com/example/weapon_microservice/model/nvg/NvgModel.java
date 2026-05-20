package com.example.weapon_microservice.model.nvg;

import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.example.weapon_microservice.model.nvg.enums.NvgGen;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "nvg")
@Builder
@Data
public class NvgModel {
    @Id
    private String id;
    private String reference;
    private String name;
    private String image;
    private NvgGen generation;
    private int range;
    private ServiceStatus status;
}
