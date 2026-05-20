package com.example.weapon_microservice.model.attachment.dto;

import com.example.weapon_microservice.model.attachment.enums.AttachmentType;
import com.example.weapon_microservice.model.common.enums.MountType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class AttachmentResponse {
    private String id;
    private String reference;
    private String name;
    private String image;
    private MountType mountType;
    private AttachmentType type;
}
