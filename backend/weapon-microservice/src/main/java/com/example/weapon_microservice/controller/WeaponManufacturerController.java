package com.example.weapon_microservice.controller;
import com.example.weapon_microservice.model.manufacturer.dto.ManufacturerRequest;
import com.example.weapon_microservice.model.manufacturer.dto.ManufacturerUpdateRequest;
import com.example.weapon_microservice.model.manufacturer.dto.WeaponManufacturerResponse;
import com.example.weapon_microservice.model.manufacturer.mapper.ManufacturerMapper;
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
}
