package com.example.weapon_microservice.controller;
import com.example.weapon_microservice.model.magazine.dto.MagazineRequest;
import com.example.weapon_microservice.model.magazine.dto.MagazineResponse;
import com.example.weapon_microservice.model.magazine.MagazineModel;
import com.example.weapon_microservice.model.magazine.dto.MagazineUpdateRequest;
import com.example.weapon_microservice.model.magazine.mapper.MagazineMapper;
import com.example.weapon_microservice.service.magazine.MagazineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armory/magazines")
public class MagazineController {
    @Autowired
    private MagazineService service;

    @GetMapping
    public List<MagazineResponse> getAllMagazines(){
        return MagazineMapper.responseFromModelListSimple(service.getAllMagazines());
    }

    @GetMapping("/reference/{reference}")
    public MagazineResponse getMagazineByReference(@PathVariable String reference){
        MagazineModel magazine = service.getByReference(reference);
        return MagazineMapper.responseFromModel(
                magazine,
                service.getPlatformsByIds(magazine.getCompatiblePlatformsIds()),
                service.getCaliberById(magazine.getCaliberId())
        );
    }

    @GetMapping("/id/{id}")
    public MagazineResponse getMagazineById(@PathVariable String id){
        MagazineModel magazine = service.getMagazineById(id);
        return MagazineMapper.responseFromModel(
                magazine,
                service.getPlatformsByIds(magazine.getCompatiblePlatformsIds()),
                service.getCaliberById(magazine.getCaliberId())
        );
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public MagazineResponse createMagazine(@Valid @RequestBody MagazineRequest magazine){
        return MagazineMapper.responseFromModel(
                service.saveMagazine(magazine),
                service.getPlatformsByIds(magazine.getCompatiblePlatformsIds()),
                service.getCaliberById(magazine.getCaliberId())
        );
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public MagazineResponse updateMagazine(@RequestBody MagazineUpdateRequest magazine, @PathVariable String id){
        MagazineModel model = service.updateMagazine(id, magazine);
        return MagazineMapper.responseFromModel(
                model,
                service.getPlatformsByIds(model.getCompatiblePlatformsIds()),
                service.getCaliberById(model.getCaliberId())
        );
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteMagazine(@PathVariable String id){
        service.deleteMagazine(id);
    }
}
