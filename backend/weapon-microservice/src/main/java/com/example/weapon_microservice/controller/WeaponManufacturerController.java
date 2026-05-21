package com.example.weapon_microservice.controller;
import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.manufacturer.WeaponManufacturerModel;
import com.example.weapon_microservice.model.manufacturer.dto.ManufacturerRequest;
import com.example.weapon_microservice.model.manufacturer.dto.ManufacturerUpdateRequest;
import com.example.weapon_microservice.model.manufacturer.dto.WeaponManufacturerResponse;
import com.example.weapon_microservice.model.manufacturer.mapper.ManufacturerMapper;
import com.example.weapon_microservice.model.textile.TextileModel;
import com.example.weapon_microservice.model.textile.dto.TextileResponse;
import com.example.weapon_microservice.model.textile.mapper.TextileMapper;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.weaponManufacturer.WeaponManufacturerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armory/manufacturers")
public class WeaponManufacturerController {
    @Autowired
    private WeaponManufacturerService service;

    @GetMapping
    public List<WeaponManufacturerResponse> getAllWeaponManufacturers(){
        return ManufacturerMapper.responseFromModelList(service.getAllWeaponManufacturers());
    }

    @GetMapping("/reference/{reference}")
    public WeaponManufacturerResponse getWeaponManufacturerByReference(@PathVariable String reference){
        return ManufacturerMapper.responseFromModel(
                service.getByReference(reference)
        );
    }

    @GetMapping("/id/{id}")
    public WeaponManufacturerResponse getWeaponManufacturerById(@PathVariable String id){
        return ManufacturerMapper.responseFromModel(
                service.getWeaponManufacturerById(id)
        );
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public WeaponManufacturerResponse createWeaponManufacturer(@Valid @RequestBody ManufacturerRequest manufacturerRequest){
        return ManufacturerMapper.responseFromModel(service.saveWeaponManufacturer(manufacturerRequest));
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public WeaponManufacturerResponse updateWeaponManufacturer(@RequestBody ManufacturerUpdateRequest manufacturerRequest, @PathVariable String id){
        return ManufacturerMapper.responseFromModel(service.updateWeaponManufacturer(manufacturerRequest, id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteWeaponManufacturer(@PathVariable String id){
        service.deleteWeaponManufacturer(id);
    }

    @PostMapping("/search")
    public PageResponse<WeaponManufacturerResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<WeaponManufacturerModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<WeaponManufacturerResponse> pageToResponse(PageResponse<WeaponManufacturerModel> page){
        return PageResponse.<WeaponManufacturerResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(ManufacturerMapper::responseFromModel)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
