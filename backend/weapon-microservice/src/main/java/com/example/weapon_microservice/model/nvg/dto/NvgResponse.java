package com.example.weapon_microservice.model.nvg.dto;

import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.example.weapon_microservice.model.nvg.enums.NvgGen;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@Builder
public class NvgResponse {
    private String id;
    private String reference;
    private String name;
    private String image;
    private NvgGen generation;
    private int range;
    private ServiceStatus status;
}
