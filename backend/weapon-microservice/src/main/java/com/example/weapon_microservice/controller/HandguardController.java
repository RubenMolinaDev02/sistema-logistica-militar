package com.example.weapon_microservice.controller;
import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.handguard.HandguardModel;
import com.example.weapon_microservice.model.handguard.dto.HandguardRequest;
import com.example.weapon_microservice.model.handguard.dto.HandguardResponse;
import com.example.weapon_microservice.model.handguard.dto.HandguardUpdateRequest;
import com.example.weapon_microservice.model.handguard.mapper.HandguardMapper;
import com.example.weapon_microservice.model.platform.dto.PlatformResponse;
import com.example.weapon_microservice.model.platform.mapper.PlatformMapper;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.handguard.HandguardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armory/handguards")
public class HandguardController {
    @Autowired
    private HandguardService service;

    @GetMapping
    public List<HandguardResponse> getAllHandguards(){
        return HandguardMapper.resposeFromModelList(service.getAllHandguards());
    }

    @GetMapping("/compatible")
    public List<HandguardResponse> getCompatible(@RequestParam String platformId){
        return HandguardMapper.resposeFromModelList(service.getCompatibleHandguards(platformId));
    }

    @GetMapping("/reference/{reference}")
    public HandguardResponse getHandguardByReference(@PathVariable String reference){
        HandguardModel handguardModel = service.getByReference(reference);
        List<PlatformResponse> platformResponses =
                PlatformMapper.responseFromModelList(
                        service.getPlatformsByIds(
                                handguardModel.getCompatiblePlatformsIds()
                        )
                );
        return HandguardMapper.responseFromModel(handguardModel, platformResponses);
    }

    @GetMapping("/id/{id}")
    public HandguardResponse getHandguardById(@PathVariable String id){
        HandguardModel handguardModel = service.getHandguardById(id);
        List<PlatformResponse> platformResponses =
                PlatformMapper.responseFromModelList(
                        service.getPlatformsByIds(
                                handguardModel.getCompatiblePlatformsIds()
                        )
                );
        return HandguardMapper.responseFromModel(handguardModel, platformResponses);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public HandguardResponse createHandguard(@Valid @RequestBody HandguardRequest handguard){
        List<PlatformResponse> platforms =
                PlatformMapper.responseFromModelList(service.getPlatformsByIds(handguard.getCompatiblePlatformsIds()));

        return HandguardMapper.responseFromModel(service.saveHandguard(handguard), platforms);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public HandguardResponse updateHandguard(@Valid @RequestBody HandguardUpdateRequest handguard, @PathVariable String id){
        List<PlatformResponse> platforms =
                PlatformMapper.responseFromModelList(service.getPlatformsByIds(handguard.getCompatiblePlatformsIds()));

        return HandguardMapper.responseFromModel(service.updateHandguard(handguard, id), platforms);
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteHandguard(@PathVariable String id){
        service.deleteHandguard(id);
    }

    @PostMapping("/search")
    public PageResponse<HandguardResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<HandguardModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<HandguardResponse> pageToResponse(PageResponse<HandguardModel> page){
        return PageResponse.<HandguardResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(HandguardMapper::responseFromModelSimple)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
