package com.example.weapon_microservice.controller;
import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.common.enums.StockAtachmentSystem;
import com.example.weapon_microservice.model.platform.mapper.PlatformMapper;
import com.example.weapon_microservice.model.stock.WeaponStockModel;
import com.example.weapon_microservice.model.stock.dto.StockRequest;
import com.example.weapon_microservice.model.platform.dto.PlatformResponse;
import com.example.weapon_microservice.model.stock.dto.StockUpdateRequest;
import com.example.weapon_microservice.model.stock.dto.WeaponStockResponse;
import com.example.weapon_microservice.model.stock.mapper.StockMapper;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.model.weapon.dto.WeaponResponse;
import com.example.weapon_microservice.model.weapon.mapper.WeaponMapper;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.stock.WeaponStockQueryBuilder;
import com.example.weapon_microservice.service.stock.WeaponStockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armory/stocks")
public class WeaponStockController {
    @Autowired
    private WeaponStockService service;

    @Autowired
    private WeaponStockQueryBuilder weaponStockQueryBuilder;

    @GetMapping
    public List<WeaponStockResponse> getAllWeaponStocks(){
        return StockMapper.responseFromModelList(service.getAllWeaponStocks());
    }

    @GetMapping("/compatible")
    public List<WeaponStockResponse> getCompatibleWeaponStocks(@RequestParam String platformId, @RequestParam StockAtachmentSystem stockAtachmentSystem){
        return StockMapper.responseFromModelList(service.getCompatibleWeaponStocks(platformId, stockAtachmentSystem));
    }

    @GetMapping("/reference/{reference}")
    public WeaponStockResponse getStockByReference(@PathVariable String reference){
        WeaponStockModel stock = service.getByReference(reference);
        List<PlatformResponse> platformResponses = PlatformMapper.responseFromModelList(
                service.getPlatformsByIds(stock.getCompatiblePlatformsIds())
        );
        return StockMapper.responseFromModel(stock, platformResponses);
    }

    @GetMapping("/id/{id}")
    public WeaponStockResponse getStockById(@PathVariable String id){
        WeaponStockModel stock = service.getWeaponStockById(id);
        List<PlatformResponse> platformResponses = PlatformMapper.responseFromModelList(
                service.getPlatformsByIds(stock.getCompatiblePlatformsIds())
        );
        return StockMapper.responseFromModel(stock, platformResponses);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public WeaponStockResponse createStock(@Valid @RequestBody StockRequest stock){
        List<PlatformResponse> platforms =
                PlatformMapper.responseFromModelList(service.getPlatformsByIds(stock.getCompatiblePlatformsIds()));

        return StockMapper.responseFromModel(service.saveWeaponStock(stock), platforms);
    }

    @PreAuthorize("hasRole('system-admin')")
    @PatchMapping("/{id}")
    public WeaponStockResponse updateStock(@RequestBody StockUpdateRequest stock, @PathVariable String id){
        List<PlatformResponse> platforms =
                PlatformMapper.responseFromModelList(service.getPlatformsByIds(stock.getCompatiblePlatformsIds()));

        return StockMapper.responseFromModel(service.updateWeaponStock(stock, id), platforms);
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable String id){
        service.deleteWeaponStock(id);
    }

    @PostMapping("/search")
    public PageResponse<WeaponStockResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<WeaponStockModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<WeaponStockResponse> pageToResponse(PageResponse<WeaponStockModel> page){
        return PageResponse.<WeaponStockResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(StockMapper::responseFromModelSimple)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}
