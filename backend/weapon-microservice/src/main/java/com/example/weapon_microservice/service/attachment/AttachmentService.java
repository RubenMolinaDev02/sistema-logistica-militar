package com.example.weapon_microservice.service.attachment;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.attachment.AttachmentModel;
import com.example.weapon_microservice.model.attachment.dto.AttachmentRequest;
import com.example.weapon_microservice.model.attachment.dto.AttachmentUpdateRequest;
import com.example.weapon_microservice.model.attachment.mapper.AttachmentMapper;
import com.example.weapon_microservice.model.barrelAtachment.BarrelAtachmentModel;
import com.example.weapon_microservice.repository.AttachmentRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AttachmentService {
    @Autowired
    private AttachmentRepository repository;
    @Autowired
    private ItemSearchService itemSearchService;
    @Autowired
    private AttachmentQueryBuilder queryBuilder;

    public AttachmentModel getAttachmentById(String id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Attachment not found"
                )
        );
    }

    public AttachmentModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Attachment not found"
                )
        );
    }

    public List<AttachmentModel> getAllAttachments() {
        return repository.findAll();
    }

    public AttachmentModel saveAttachment(AttachmentRequest attachmentModel){
        return repository.save(AttachmentMapper.modelFromRequest(attachmentModel, Utils.generateId("ATCH"), Utils.referenceGenerator("ATCH")));
    }

    public AttachmentModel updateAttachment(AttachmentUpdateRequest attachment, String id) {
        AttachmentModel exists = getAttachmentById(id);

        itemSearchService.validateReference(attachment.getReference(), exists.getReference());
        if (Utils.validateString(attachment.getReference())) exists.setReference(attachment.getReference());

        if (Utils.validateString(attachment.getName())) exists.setName(attachment.getName());
        if (Utils.validateString(attachment.getImage())) exists.setImage(attachment.getImage());
        if (attachment.getType() != null) exists.setType(attachment.getType());
        if (attachment.getMountType() != null) exists.setMountType(attachment.getMountType());

        return repository.save(exists);
    }

    public void deleteAttachment(String id) {
        AttachmentModel attachmentModel = getAttachmentById(id);
        repository.delete(attachmentModel);
    }

    public PageResponse<AttachmentModel> search(SearchRequest request, int page, int size) {
        return queryBuilder.search(request, page, size);
    }
}
