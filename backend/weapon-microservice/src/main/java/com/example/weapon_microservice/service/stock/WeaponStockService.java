package com.example.weapon_microservice.service.stock;

import com.example.weapon_microservice.model.stock.dto.StockRequest;
import com.example.weapon_microservice.model.platform.PlatformModel;
import com.example.weapon_microservice.model.stock.WeaponStockModel;
import com.example.weapon_microservice.model.stock.dto.StockUpdateRequest;
import com.example.weapon_microservice.model.stock.mapper.StockMapper;
import com.example.weapon_microservice.repository.WeaponStockRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.platform.PlatformService;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WeaponStockService {
    @Autowired
    private WeaponStockRepository repository;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private ItemSearchService itemSearchService;

    public WeaponStockModel getWeaponStockById(String id){
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Stock not found"
                        )
                );
    }

    public WeaponStockModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Stock not found"
                )
        );
    }

    public List<WeaponStockModel> getAllWeaponStocks() {
        return repository.findAll();
    }

    public WeaponStockModel saveWeaponStock(StockRequest model){
        return repository.save(StockMapper.modelFromRequest(model, Utils.generateId("STOCK"), Utils.referenceGenerator("STOCK")));
    }


    public List<PlatformModel> getPlatformsByIds(List<String> compatiblePlatformsIds) {
        return platformService.getAllPlatformsById(compatiblePlatformsIds);
    }

    public void deleteWeaponStock(String id) {
        WeaponStockModel stockModel = getWeaponStockById(id);

        repository.delete(stockModel);
    }

    public WeaponStockModel updateWeaponStock(StockUpdateRequest stock, String id) {
        WeaponStockModel exists = getWeaponStockById(id);

        itemSearchService.validateReference(stock.getReference());

        exists.setCompatiblePlatformsIds(filterExistingPlatformIds(stock.getCompatiblePlatformsIds()));

        if (Utils.validateString(stock.getName())) exists.setName(stock.getName());

        if (Utils.validateString(stock.getReference())) exists.setReference(stock.getReference());

        if (stock.getMaterial() != null) exists.setMaterial(stock.getMaterial());

        if (stock.getType() != null) exists.setType(stock.getType());

        if (stock.getAtachmentMethod() != null) exists.setAtachmentMethod(stock.getAtachmentMethod());

        return repository.save(exists);
    }

    private List<String> filterExistingPlatformIds(List<String> ids) {

        List<PlatformModel> platforms = getPlatformsByIds(ids);

        return platforms.stream()
                .map(PlatformModel::getId)
                .toList();
    }
}
