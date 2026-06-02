package com.example.weapon_microservice.controller;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.miscItems.MiscItemModel;
import com.example.weapon_microservice.model.miscItems.dto.MiscItemResponse;
import com.example.weapon_microservice.model.miscItems.mapper.MiscItemMapper;
import com.example.weapon_microservice.model.stock.WeaponStockModel;
import com.example.weapon_microservice.model.textile.TextileModel;
import com.example.weapon_microservice.model.textile.dto.TextileRequest;
import com.example.weapon_microservice.model.textile.dto.TextileResponse;
import com.example.weapon_microservice.model.textile.dto.TextileUpdateRequest;
import com.example.weapon_microservice.model.textile.mapper.TextileMapper;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.model.weapon.dto.WeaponResponse;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.textile.TextileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/armory/textile")
public class TextileController {
    @Autowired
    private TextileService service;

    @GetMapping
    public List<TextileResponse> getAllTextile(){
        return TextileMapper.responseFromModelList(service.getAllTextile());
    }

    @GetMapping("/model/{id}")
    public TextileModel getTextileByIdModel(@PathVariable String id){
        return service.getTextileById(id);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public TextileResponse createTextile(@Valid @RequestBody TextileRequest textile){
        TextileModel model = service.saveTextile(textile);
        return TextileMapper.responseFromModel(model);
    }

    @GetMapping("/reference/{reference}")
    public TextileResponse getTextileByReference(@PathVariable String reference){
        TextileModel textile = service.getByReference(reference);
        return TextileMapper.responseFromModel(textile);
    }

    @GetMapping("/id/{id}")
    public TextileResponse getTextileById(@PathVariable String id){
        TextileModel textile = service.getTextileById(id);
        return TextileMapper.responseFromModel(textile);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public TextileResponse updateTextile(@PathVariable String id, @RequestBody TextileUpdateRequest Textile){
        TextileModel textile = service.updateTextile(Textile, id);
        return TextileMapper.responseFromModel(textile);
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteTextile(@PathVariable String id){
        service.deleteTextile(id);
    }

    @PostMapping("/search")
    public PageResponse<TextileResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<TextileModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<TextileResponse> pageToResponse(PageResponse<TextileModel> page){
        return PageResponse.<TextileResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(TextileMapper::responseFromModel)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
