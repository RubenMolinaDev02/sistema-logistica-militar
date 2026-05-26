package com.example.weapon_microservice.controller;
import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.platform.PlatformModel;
import com.example.weapon_microservice.model.platform.dto.PlatformRequest;
import com.example.weapon_microservice.model.platform.dto.PlatformResponse;
import com.example.weapon_microservice.model.platform.dto.PlatformUpdateRequest;
import com.example.weapon_microservice.model.platform.mapper.PlatformMapper;
import com.example.weapon_microservice.model.stock.WeaponStockModel;
import com.example.weapon_microservice.model.stock.dto.WeaponStockResponse;
import com.example.weapon_microservice.model.stock.mapper.StockMapper;
import com.example.weapon_microservice.model.weapon.dto.WeaponResponse;
import com.example.weapon_microservice.model.weapon.mapper.WeaponMapper;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.platform.PlatformService;
import com.example.weapon_microservice.service.weapon.WeaponService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armory/platforms")
public class PlatformController {
    @Autowired
    private PlatformService service;
    @Autowired
    private WeaponService weaponService;

    @GetMapping
    public List<PlatformResponse> getAllPlatforms(){
        return PlatformMapper.responseFromModelList(service.getAllPlatforms());
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public PlatformResponse createPlatform(@Valid @RequestBody PlatformRequest platform){
        return PlatformMapper.responseFromModel(service.savePlatform(platform));
    }

    @GetMapping("/reference/{reference}")
    public PlatformResponse getPlatformByReference(@PathVariable String reference){
        return PlatformMapper.responseFromModel(service.getByReference(reference));
    }

    @GetMapping("/id/{id}")
    public PlatformResponse getPlatformById(@PathVariable String id){
        List<WeaponResponse> weapons =
                WeaponMapper.responseFromModelSimpleList(weaponService.getByPlatformId(id));
        return PlatformMapper.responseFromModelDetail(service.getPlatformById(id), weapons);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public PlatformResponse updatePlatform(@PathVariable String id, @RequestBody PlatformUpdateRequest platform){
        return PlatformMapper.responseFromModel(service.updatePlatform(platform, id));
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deletePlatform(@PathVariable String id){
        service.deletePlatform(id);
    }

    @PostMapping("/search")
    public PageResponse<PlatformResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<PlatformModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<PlatformResponse> pageToResponse(PageResponse<PlatformModel> page){
        return PageResponse.<PlatformResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(PlatformMapper::responseFromModel)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
