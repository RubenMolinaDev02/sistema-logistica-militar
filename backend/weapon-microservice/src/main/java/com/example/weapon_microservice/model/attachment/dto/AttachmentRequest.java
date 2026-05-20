package com.example.weapon_microservice.model.attachment.dto;

import com.example.weapon_microservice.model.attachment.enums.AttachmentType;
import com.example.weapon_microservice.model.common.enums.MountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AttachmentRequest {
    @NotBlank
    private String name;
    @NotNull
    private MountType mountType;
    @NotNull
    private AttachmentType type;
    @NotNull
    private String image;
}
