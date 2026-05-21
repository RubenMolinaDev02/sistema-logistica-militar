package com.example.weapon_microservice.controller;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.grenade.GrenadeModel;
import com.example.weapon_microservice.model.grenade.dto.GrenadeRequest;
import com.example.weapon_microservice.model.grenade.dto.GrenadeResponse;
import com.example.weapon_microservice.model.grenade.dto.GrenadeUpdateRequest;
import com.example.weapon_microservice.model.grenade.mapper.GrenadeMapper;
import com.example.weapon_microservice.model.holster.HolsterModel;
import com.example.weapon_microservice.model.holster.dto.HolsterResponse;
import com.example.weapon_microservice.model.holster.mapper.HolsterMapper;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.grenade.GrenadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/armory/grenades")
public class GrenadeController {
    @Autowired
    private GrenadeService service;

    @GetMapping
    public List<GrenadeResponse> getAllGrenades(){
        return GrenadeMapper.responseFromModelList(service.getAllGrenades());
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public GrenadeResponse createGrenade(@Valid @RequestBody GrenadeRequest grenade){
        return GrenadeMapper.responseFromModel(service.saveGrenade(grenade));
    }

    @GetMapping("/reference/{reference}")
    public GrenadeResponse getGrenadeByReference(@PathVariable String reference){
        return GrenadeMapper.responseFromModel(service.getByReference(reference));
    }

    @GetMapping("/id/{id}")
    public GrenadeResponse getGrenadeById(@PathVariable String id){
        return GrenadeMapper.responseFromModel(service.getGrenadeById(id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public GrenadeResponse updateGrenade(@PathVariable String id, @RequestBody GrenadeUpdateRequest grenade){
        return GrenadeMapper.responseFromModel(service.updateGrenade(grenade, id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteGrenade(@PathVariable String id){
        service.deleteGrenade(id);
    }

    @PostMapping("/search")
    public PageResponse<GrenadeResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<GrenadeModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<GrenadeResponse> pageToResponse(PageResponse<GrenadeModel> page){
        return PageResponse.<GrenadeResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(GrenadeMapper::responseFromModel)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
