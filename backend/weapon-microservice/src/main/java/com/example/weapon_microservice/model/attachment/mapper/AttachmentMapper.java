package com.example.weapon_microservice.model.attachment.mapper;


import com.example.weapon_microservice.model.attachment.AttachmentModel;
import com.example.weapon_microservice.model.attachment.dto.AttachmentRequest;
import com.example.weapon_microservice.model.attachment.dto.AttachmentResponse;
import com.example.weapon_microservice.model.barrelAtachment.dto.BarrelAtachmentRequest;

import java.util.List;
import java.util.stream.Collectors;

public class AttachmentMapper {
    public static AttachmentModel modelFromRequest(AttachmentRequest atachmentRequest, String id, String reference){
        return AttachmentModel.builder()
                .id(id)
                .reference(reference)
                .name(atachmentRequest.getName())
                .image(atachmentRequest.getImage())
                .mountType(atachmentRequest.getMountType())
                .type(atachmentRequest.getType())
                .build();
    }
    public static AttachmentResponse responseFromModel(AttachmentModel attachmentModel){
        return AttachmentResponse.builder()
                .id(attachmentModel.getId())
                .reference(attachmentModel.getReference())
                .name(attachmentModel.getName())
                .image(attachmentModel.getImage())
                .mountType(attachmentModel.getMountType())
                .type(attachmentModel.getType())
                .build();
    }
    public static List<AttachmentResponse> responseFromModelList(List<AttachmentModel> attachmentModels){
        return attachmentModels.stream()
                .map(AttachmentMapper::responseFromModel)
                .collect(Collectors.toList());
    }
}
