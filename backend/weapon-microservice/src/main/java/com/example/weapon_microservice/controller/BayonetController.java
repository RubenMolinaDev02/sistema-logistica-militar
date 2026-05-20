package com.example.weapon_microservice.controller;
import com.example.weapon_microservice.model.bayonet.BayonetModel;
import com.example.weapon_microservice.model.bayonet.dto.BayonetRequest;
import com.example.weapon_microservice.model.bayonet.dto.BayonetResponse;
import com.example.weapon_microservice.model.bayonet.dto.BayonetUpdateRequest;
import com.example.weapon_microservice.model.bayonet.mapper.BayonetMapper;
import com.example.weapon_microservice.service.bayonet.BayonetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armory/bayonets")
public class BayonetController {
    @Autowired
    private BayonetService service;

    @GetMapping
    public List<BayonetResponse> getAllBayonets(){
        return BayonetMapper.responseFromModelListSimple(service.getAllBayonets());
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public BayonetResponse createBayonet(@Valid @RequestBody BayonetRequest bayonet){
        return BayonetMapper.responseFromModel(service.saveBayonet(bayonet), service.getWeaponsByIds(bayonet.getCompatibleWeaponsIds()));
    }

    @GetMapping("/reference/{reference}")
    public BayonetResponse getBayonetByReference(@PathVariable String reference){
        BayonetModel model = service.getByReference(reference);
        return BayonetMapper.responseFromModel(model, service.getWeaponsByIds(model.getCompatibleWeaponsIds()));
    }

    @GetMapping("/id/{id}")
    public BayonetResponse getBayonetById(@PathVariable String id){
        BayonetModel model = service.getBayonetById(id);
        return BayonetMapper.responseFromModel(model, service.getWeaponsByIds(model.getCompatibleWeaponsIds()));
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public BayonetResponse updateBayonet(@PathVariable String id, @RequestBody BayonetUpdateRequest bayonet){
        BayonetModel model = service.updateBayonet(bayonet, id);
        return BayonetMapper.responseFromModel(model, service.getWeaponsByIds(model.getCompatibleWeaponsIds()));
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteBayonet(@PathVariable String id){
        service.deleteBayonet(id);
    }
}
