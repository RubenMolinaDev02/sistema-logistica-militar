package com.example.weapon_microservice.controller;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.armorPlate.ArmorPlateModel;
import com.example.weapon_microservice.model.armorPlate.dto.ArmorPlateRequest;
import com.example.weapon_microservice.model.armorPlate.dto.ArmorPlateResponse;
import com.example.weapon_microservice.model.armorPlate.dto.ArmorPlateUpdateRequest;
import com.example.weapon_microservice.model.armorPlate.mapper.ArmorPlateMapper;
import com.example.weapon_microservice.model.armorVest.ArmorVestModel;
import com.example.weapon_microservice.model.armorVest.dto.ArmorVestResponse;
import com.example.weapon_microservice.model.armorVest.mapper.ArmorVestMapper;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.armorPlate.ArmorPlateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armory/plates")
public class ArmorPlateController {
    @Autowired
    private ArmorPlateService service;

    @GetMapping
    public List<ArmorPlateResponse> getAllArmorPlate(){
        return ArmorPlateMapper.responseFromModelList(service.getAllArmorPlate());
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public ArmorPlateResponse createArmorPlate(@Valid @RequestBody ArmorPlateRequest armorPlate){
        ArmorPlateModel model = service.saveArmorPlate(armorPlate);
        return ArmorPlateMapper.responseFromModel(model);
    }

    @GetMapping("/reference/{reference}")
    public ArmorPlateResponse getArmorPlateByReference(@PathVariable String reference){
        ArmorPlateModel armorPlate = service.getByReference(reference);
        return ArmorPlateMapper.responseFromModel(armorPlate);
    }

    @GetMapping("/id/{id}")
    public ArmorPlateResponse getArmorPlateById(@PathVariable String id){
        ArmorPlateModel armorPlate = service.getArmorPlateById(id);
        return ArmorPlateMapper.responseFromModel(armorPlate);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public ArmorPlateResponse updateArmorPlate(@PathVariable String id, @RequestBody ArmorPlateUpdateRequest armorPlateUpdateRequest){
        ArmorPlateModel armorPlate = service.updateArmorPlate(armorPlateUpdateRequest, id);
        return ArmorPlateMapper.responseFromModel(armorPlate);
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteArmorPlate(@PathVariable String id){
        service.deleteArmorPlate(id);
    }

    @PostMapping("/search")
    public PageResponse<ArmorPlateResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<ArmorPlateModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<ArmorPlateResponse> pageToResponse(PageResponse<ArmorPlateModel> page){
        return PageResponse.<ArmorPlateResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(ArmorPlateMapper::responseFromModel)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
