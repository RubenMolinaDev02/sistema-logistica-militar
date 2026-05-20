package com.example.weapon_microservice.model.nvg.dto;

import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.example.weapon_microservice.model.nvg.enums.NvgGen;
import lombok.Data;

@Data
public class NvgUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private NvgGen generation;
    private Integer range;
    private ServiceStatus status;
}
