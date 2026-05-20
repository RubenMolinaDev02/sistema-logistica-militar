package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.attachment.AttachmentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AttachmentRepository  extends MongoRepository<AttachmentModel, String> {
    AttachmentModel getAttachmentModelByNameEqualsIgnoreCase(String name);

    Optional<AttachmentModel> readByReference(String reference);
}
