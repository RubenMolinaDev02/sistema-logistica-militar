package com.example.weapon_microservice.controller;

import com.example.weapon_microservice.model.armorVest.ArmorVestModel;
import com.example.weapon_microservice.model.armorVest.dto.ArmorVestRequest;
import com.example.weapon_microservice.model.armorVest.dto.ArmorVestResponse;
import com.example.weapon_microservice.model.armorVest.dto.ArmorVestUpdateRequest;
import com.example.weapon_microservice.model.armorVest.mapper.ArmorVestMapper;
import com.example.weapon_microservice.service.armorVest.ArmorVestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/armory/vests")
public class ArmorVestController {
    @Autowired
    private ArmorVestService service;

    @GetMapping
    public List<ArmorVestResponse> getAllArmorVest(){
        return ArmorVestMapper.responseFromModelList(service.getAllArmorVest());
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public ArmorVestResponse createArmorVest(@Valid @RequestBody ArmorVestRequest armorVest){
        ArmorVestModel model = service.saveArmorVest(armorVest);
        return ArmorVestMapper.responseFromModel(model);
    }

    @GetMapping("/reference/{reference}")
    public ArmorVestResponse getArmorVestByReference(@PathVariable String reference){
        ArmorVestModel armorVest = service.getByReference(reference);
        return ArmorVestMapper.responseFromModel(armorVest);
    }

    @GetMapping("/id/{id}")
    public ArmorVestResponse getArmorVestById(@PathVariable String id){
        ArmorVestModel armorVest = service.getArmorVestById(id);
        return ArmorVestMapper.responseFromModel(armorVest);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public ArmorVestResponse updateArmorVest(@PathVariable String id, @RequestBody ArmorVestUpdateRequest ArmorVest){
        ArmorVestModel armorVest = service.updateArmorVest(ArmorVest, id);
        return ArmorVestMapper.responseFromModel(armorVest);
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteArmorVest(@PathVariable String id){
        service.deleteArmorVest(id);
    }
}
