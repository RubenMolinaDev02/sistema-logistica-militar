package com.example.weapon_microservice.model.attachment.dto;

import com.example.weapon_microservice.model.attachment.enums.AttachmentType;
import com.example.weapon_microservice.model.common.enums.MountType;
import lombok.Data;

@Data
public class AttachmentUpdateRequest {
    private String reference;
    private String name;
    private String image;
    private MountType mountType;
    private AttachmentType type;
}
