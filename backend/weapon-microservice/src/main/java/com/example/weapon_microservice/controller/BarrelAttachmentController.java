package com.example.weapon_microservice.controller;
import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.barrelAtachment.BarrelAtachmentModel;
import com.example.weapon_microservice.model.barrelAtachment.dto.BarrelAtachmentRequest;
import com.example.weapon_microservice.model.barrelAtachment.dto.BarrelAtachmentResponse;
import com.example.weapon_microservice.model.barrelAtachment.dto.BarrelAtachmentUpdateRequest;
import com.example.weapon_microservice.model.barrelAtachment.mapper.BarrelAtachmentMapper;
import com.example.weapon_microservice.model.bayonet.BayonetModel;
import com.example.weapon_microservice.model.bayonet.dto.BayonetResponse;
import com.example.weapon_microservice.model.bayonet.mapper.BayonetMapper;
import com.example.weapon_microservice.model.caliber.CaliberModel;
import com.example.weapon_microservice.model.caliber.mapper.CaliberMapper;
import com.example.weapon_microservice.model.platform.PlatformModel;
import com.example.weapon_microservice.model.platform.mapper.PlatformMapper;
import com.example.weapon_microservice.model.textile.TextileModel;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.barrelAtachment.BarrelAtachmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armory/barrel-attachments")
public class BarrelAttachmentController {
    @Autowired
    private BarrelAtachmentService service;

    @GetMapping
    public List<BarrelAtachmentResponse> getAllBarrelAtachments(){
        return BarrelAtachmentMapper.responseFromModelListSimple(service.getAllBarrelAtachments());
    }

    @GetMapping("/model/{id}")
    public BarrelAtachmentModel getBarrelAtachmentByIdModel(@PathVariable String id){
        return service.getBarrelAtachmentById(id);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public BarrelAtachmentResponse createBarrelAtachment(@Valid @RequestBody BarrelAtachmentRequest barrelAtachment){
        BarrelAtachmentModel barrelAtachmentModel = service.saveBarrelAtachment(barrelAtachment);
        CaliberModel caliberModel = service.getCaliberById(barrelAtachmentModel.getCompatibleCaliber());
        List<PlatformModel> platformModels = service.getPlatformsByIds(barrelAtachmentModel.getCompatiblePlatformsIds());

        return BarrelAtachmentMapper.responseFromModel(
                barrelAtachmentModel,
                CaliberMapper.responseFromModelSimple(caliberModel),
                PlatformMapper.responseFromModelList(platformModels)
        );
    }

    @GetMapping("/reference/{reference}")
    public BarrelAtachmentResponse getBarrelAtachmentByReference(@PathVariable String reference){
        BarrelAtachmentModel model = service.getByReference(reference);
        CaliberModel caliberModel = service.getCaliberById(model.getCompatibleCaliber());
        List<PlatformModel> platformModels = service.getPlatformsByIds(model.getCompatiblePlatformsIds());

        return BarrelAtachmentMapper.responseFromModel(
                model,
                CaliberMapper.responseFromModelSimple(caliberModel),
                PlatformMapper.responseFromModelList(platformModels)
        );
    }

    @GetMapping("/id/{id}")
    public BarrelAtachmentResponse getBarrelAtachmentById(@PathVariable String id){
        BarrelAtachmentModel model = service.getBarrelAtachmentById(id);
        CaliberModel caliberModel = service.getCaliberById(model.getCompatibleCaliber());
        List<PlatformModel> platformModels = service.getPlatformsByIds(model.getCompatiblePlatformsIds());

        return BarrelAtachmentMapper.responseFromModel(
                model,
                CaliberMapper.responseFromModelSimple(caliberModel),
                PlatformMapper.responseFromModelList(platformModels)
        );
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public BarrelAtachmentResponse updateBarrelAtachment(@PathVariable String id, @RequestBody BarrelAtachmentUpdateRequest barrelAtachment){
        BarrelAtachmentModel model = service.updateBarrelAtachment(barrelAtachment, id);
        CaliberModel caliberModel = service.getCaliberById(model.getCompatibleCaliber());
        List<PlatformModel> platformModels = service.getPlatformsByIds(model.getCompatiblePlatformsIds());

        return BarrelAtachmentMapper.responseFromModel(
                model,
                CaliberMapper.responseFromModelSimple(caliberModel),
                PlatformMapper.responseFromModelList(platformModels)
        );
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteBarrelAtachment(@PathVariable String id){
        service.deleteBarrelAtachment(id);
    }

    @PostMapping("/search")
    public PageResponse<BarrelAtachmentResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<BarrelAtachmentModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<BarrelAtachmentResponse> pageToResponse(PageResponse<BarrelAtachmentModel> page){
        return PageResponse.<BarrelAtachmentResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(BarrelAtachmentMapper::responseFromModelSimple)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
