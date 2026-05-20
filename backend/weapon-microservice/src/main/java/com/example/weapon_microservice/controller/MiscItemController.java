package com.example.weapon_microservice.controller;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.miscItems.MiscItemModel;
import com.example.weapon_microservice.model.miscItems.dto.MiscItemRequest;
import com.example.weapon_microservice.model.miscItems.dto.MiscItemResponse;
import com.example.weapon_microservice.model.miscItems.dto.MiscItemUpdateRequest;
import com.example.weapon_microservice.model.miscItems.mapper.MiscItemMapper;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.model.weapon.dto.WeaponResponse;
import com.example.weapon_microservice.model.weapon.mapper.WeaponMapper;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.miscItem.MiscItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/armory/misc")
public class MiscItemController {
    @Autowired
    private MiscItemService service;

    @GetMapping
    public PageResponse<MiscItemResponse> getAllMiscItem(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return pageToResponse(service.getAllMiscItem(page, size));
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public MiscItemResponse createMiscItem(@Valid @RequestBody MiscItemRequest miscItem){
        MiscItemModel model = service.saveMiscItem(miscItem);
        return MiscItemMapper.responseFromModel(model);
    }

    @GetMapping("/reference/{reference}")
    public MiscItemResponse getMiscItemByReference(@PathVariable String reference){
        MiscItemModel miscItem = service.getByReference(reference);
        return MiscItemMapper.responseFromModel(miscItem);
    }

    @GetMapping("/id/{id}")
    public MiscItemResponse getMiscItemById(@PathVariable String id){
        MiscItemModel miscItem = service.getMiscItemById(id);
        return MiscItemMapper.responseFromModel(miscItem);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public MiscItemResponse updateMiscItem(@PathVariable String id, @RequestBody MiscItemUpdateRequest MiscItem){
        MiscItemModel miscItem = service.updateMiscItem(MiscItem, id);
        return MiscItemMapper.responseFromModel(miscItem);
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteMiscItem(@PathVariable String id){
        service.deleteMiscItem(id);
    }

    public PageResponse<MiscItemResponse> pageToResponse(PageResponse<MiscItemModel> page){
        return PageResponse.<MiscItemResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(MiscItemMapper::responseFromModel)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }

    @PostMapping("/search")
    public PageResponse<MiscItemResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<MiscItemModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }
}
