package com.example.weapon_microservice.controller;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.ammo.AmmoModel;
import com.example.weapon_microservice.model.ammo.dto.AmmoRequest;
import com.example.weapon_microservice.model.ammo.dto.AmmoResponse;
import com.example.weapon_microservice.model.ammo.dto.AmmoUpdateRequest;
import com.example.weapon_microservice.model.ammo.mapper.AmmoMapper;
import com.example.weapon_microservice.model.caliber.dto.CaliberResponse;
import com.example.weapon_microservice.model.caliber.mapper.CaliberMapper;
import com.example.weapon_microservice.model.handguard.HandguardModel;
import com.example.weapon_microservice.model.handguard.dto.HandguardResponse;
import com.example.weapon_microservice.model.handguard.mapper.HandguardMapper;
import com.example.weapon_microservice.model.textile.TextileModel;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.ammo.AmmoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/armory/ammo")
public class AmmoController {
    @Autowired
    private AmmoService service;

    @GetMapping
    public List<AmmoResponse> getAllAmmo(){
        return AmmoMapper.responseFromModelListSimple(service.getAllAmmo());
    }

    @GetMapping("/reference/{reference}")
    public AmmoResponse getAmmoByReference(@PathVariable String reference){
        AmmoModel ammo = service.getByReference(reference);
        CaliberResponse caliber = CaliberMapper.responseFromModelSimple(
                service.getCaliberById(ammo.getCaliberId())
        );
        return AmmoMapper.responseFromModel(ammo, caliber);
    }

    @GetMapping("/model/{id}")
    public AmmoModel getAmmoByIdModel(@PathVariable String id){
        return service.getAmmoById(id);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public AmmoResponse createAmmo(@Valid @RequestBody AmmoRequest ammo){
        AmmoModel model = service.saveAmmo(ammo);
        CaliberResponse caliber = CaliberMapper.responseFromModelSimple(
                service.getCaliberById(ammo.getCaliberId())
        );
        return AmmoMapper.responseFromModel(model, caliber);
    }

    @GetMapping("/id/{id}")
    public AmmoResponse getAmmoById(@PathVariable String id){
        AmmoModel ammo = service.getAmmoById(id);
        CaliberResponse caliber = CaliberMapper.responseFromModelSimple(
                service.getCaliberById(ammo.getCaliberId())
        );
        return AmmoMapper.responseFromModel(ammo, caliber);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public AmmoResponse updateAmmo(@PathVariable String id, @RequestBody AmmoUpdateRequest ammoUpdateRequest){
        AmmoModel ammo = service.updateAmmo(ammoUpdateRequest, id);
        CaliberResponse caliber = CaliberMapper.responseFromModelSimple(
                service.getCaliberById(ammo.getCaliberId())
        );

        return AmmoMapper.responseFromModel(ammo, caliber);
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteAmmo(@PathVariable String id){
        service.deleteAmmo(id);
    }

    @PostMapping("/search")
    public PageResponse<AmmoResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<AmmoModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<AmmoResponse> pageToResponse(PageResponse<AmmoModel> page){
        return PageResponse.<AmmoResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(AmmoMapper::responseFromModelSimple)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
