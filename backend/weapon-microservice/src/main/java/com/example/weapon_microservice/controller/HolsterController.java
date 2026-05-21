package com.example.weapon_microservice.controller;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.holster.HolsterModel;
import com.example.weapon_microservice.model.holster.dto.HolsterRequest;
import com.example.weapon_microservice.model.holster.dto.HolsterResponse;
import com.example.weapon_microservice.model.holster.dto.HolsterUpdateRequest;
import com.example.weapon_microservice.model.holster.mapper.HolsterMapper;
import com.example.weapon_microservice.model.magazine.MagazineModel;
import com.example.weapon_microservice.model.magazine.dto.MagazineResponse;
import com.example.weapon_microservice.model.magazine.mapper.MagazineMapper;
import com.example.weapon_microservice.model.weapon.dto.WeaponResponse;
import com.example.weapon_microservice.model.weapon.mapper.WeaponMapper;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.holster.HolsterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armory/holsters")
public class HolsterController {
    @Autowired
    private HolsterService service;

    @GetMapping
    public List<HolsterResponse> getAllHolster(){
        return HolsterMapper.responseFromModelSimpleList(service.getAllHolster());
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public HolsterResponse createHolster(@Valid @RequestBody HolsterRequest holster){
        HolsterModel model = service.saveHolster(holster);
        List<WeaponResponse> weapons = WeaponMapper.responseFromModelSimpleList(
                service.getWeaponsById(holster.getCompatibleWeaponIds())
        );
        return HolsterMapper.responseFromModel(model, weapons);
    }

    @GetMapping("/reference/{reference}")
    public HolsterResponse getHolsterByReference(@PathVariable String reference){
        HolsterModel model = service.getByReference(reference);
        List<WeaponResponse> weapons = WeaponMapper.responseFromModelSimpleList(
                service.getWeaponsById(model.getCompatibleWeaponIds())
        );
        return HolsterMapper.responseFromModel(model, weapons);
    }

    @GetMapping("/id/{id}")
    public HolsterResponse getHolsterById(@PathVariable String id){
        HolsterModel model = service.getHolsterById(id);
        List<WeaponResponse> weapons = WeaponMapper.responseFromModelSimpleList(
                service.getWeaponsById(model.getCompatibleWeaponIds())
        );
        return HolsterMapper.responseFromModel(model, weapons);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public HolsterResponse updateHolster(@PathVariable String id, @RequestBody HolsterUpdateRequest holster){
        HolsterModel model = service.updateHolster(holster, id);
        List<WeaponResponse> weapons = WeaponMapper.responseFromModelSimpleList(
                service.getWeaponsById(model.getCompatibleWeaponIds())
        );
        return HolsterMapper.responseFromModel(model, weapons);
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteHolster(@PathVariable String id){
        service.deleteHolster(id);
    }

    @PostMapping("/search")
    public PageResponse<HolsterResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<HolsterModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<HolsterResponse> pageToResponse(PageResponse<HolsterModel> page){
        return PageResponse.<HolsterResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(HolsterMapper::responseFromModelSimple)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
