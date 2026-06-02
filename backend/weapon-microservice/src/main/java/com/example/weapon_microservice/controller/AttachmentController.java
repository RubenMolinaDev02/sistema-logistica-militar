package com.example.weapon_microservice.controller;
import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.attachment.AttachmentModel;
import com.example.weapon_microservice.model.attachment.dto.AttachmentRequest;
import com.example.weapon_microservice.model.attachment.dto.AttachmentResponse;
import com.example.weapon_microservice.model.attachment.dto.AttachmentUpdateRequest;
import com.example.weapon_microservice.model.attachment.mapper.AttachmentMapper;
import com.example.weapon_microservice.model.bayonet.BayonetModel;
import com.example.weapon_microservice.model.bayonet.dto.BayonetResponse;
import com.example.weapon_microservice.model.bayonet.mapper.BayonetMapper;
import com.example.weapon_microservice.model.textile.TextileModel;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.attachment.AttachmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armory/attachments")
public class AttachmentController {
    @Autowired
    private AttachmentService service;

    @GetMapping
    public List<AttachmentResponse> getAllAttachments(){
        return AttachmentMapper.responseFromModelList(service.getAllAttachments());
    }

    @GetMapping("/model/{id}")
    public AttachmentModel getAttachmentByIdModel(@PathVariable String id){
        return service.getAttachmentById(id);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public AttachmentResponse createAttachment(@Valid @RequestBody AttachmentRequest attachment){
        return AttachmentMapper.responseFromModel(service.saveAttachment(attachment));
    }

    @GetMapping("/reference/{reference}")
    public AttachmentResponse getAttachmentByReference(@PathVariable String reference){
        return AttachmentMapper.responseFromModel(service.getByReference(reference));
    }

    @GetMapping("/id/{id}")
    public AttachmentResponse getAttachmentById(@PathVariable String id){
        return AttachmentMapper.responseFromModel(service.getAttachmentById(id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public AttachmentResponse updateAttachment(@PathVariable String id, @RequestBody AttachmentUpdateRequest attachment){
        return AttachmentMapper.responseFromModel(service.updateAttachment(attachment, id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteAttachment(@PathVariable String id){
        service.deleteAttachment(id);
    }

    @PostMapping("/search")
    public PageResponse<AttachmentResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<AttachmentModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<AttachmentResponse> pageToResponse(PageResponse<AttachmentModel> page){
        return PageResponse.<AttachmentResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(AttachmentMapper::responseFromModel)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
