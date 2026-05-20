package com.example.inventory_microservice.service;

import com.example.inventory_microservice.client.LocationClient;
import com.example.inventory_microservice.model.sku.SkuModel;
import com.example.inventory_microservice.model.stock.StockModel;
import com.example.inventory_microservice.model.stock.dto.StockRequest;
import com.example.inventory_microservice.model.stock.dto.StockUpdateRequest;
import com.example.inventory_microservice.model.stock.mapper.StockMapper;
import com.example.inventory_microservice.repository.StockRepository;
import com.example.inventory_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    @Autowired
    private LocationClient locationClient;

    public StockModel getById(String id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found")
        );
    }

    public StockModel getByReference(String ref){
        return repository.findByReference(ref).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found")
        );
    }

    public List<StockModel> getAll() {
        return repository.findAll();
    }

    public List<StockModel> getBySkuId(String skuId) {
        return repository.findAllBySkuId(skuId);
    }

    public List<StockModel> getByLocationId(String locationId) {
        return repository.findAllByLocationId(locationId);
    }

    public boolean existsBySkuAndLocation(String skuId, String locationId){
        return repository.existsBySkuIdAndLocationId(skuId, locationId);
    }

    public StockModel getBySkuAndLocation(String skuId, String locationId){
        return repository.findBySkuIdAndLocationId(skuId, locationId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Stock not found"
                )
        );
    }

    public int getUnserializedStockInLocation(String skuId, String locationId){
        return getBySkuAndLocation(skuId, locationId).getUnits();
    }

    public int getUnserializedStockGeneral(String skuId){
        return getBySkuId(skuId).stream()
                .mapToInt(StockModel::getUnits)
                .sum();
    }

    public StockModel createStockInternal(SkuModel sku, String locationId){
        if (sku.isSerialized())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Stock is serialized"
            );

        if (existsBySkuAndLocation(sku.getId(), locationId))
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Stock already exists"
            );
        StockModel model = StockModel.builder()
                .id(Utils.generateId("STOCK"))
                .reference(Utils.referenceGenerator("STOCK"))
                .skuId(sku.getId())
                .locationId(locationId)
                .units(0)
                .build();
        return repository.save(model);
    }

    public StockModel createStock(StockRequest request, String skuId) {
        locationClient.getById(request.getLocationId());

        if (existsBySkuAndLocation(skuId, request.getLocationId()))
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Stock already exists"
        );

        StockModel model = StockMapper.modelFromRequest(
                request,
                Utils.generateId("STOCK"),
                skuId,
                request.getLocationId(),
                Utils.referenceGenerator("STOCK")
        );

        return repository.save(model);
    }

    public StockModel updateStock(String id, StockUpdateRequest request) {

        StockModel existing = getById(id);

        if (request.getReference() != null &&
                repository.existsByReference(request.getReference()))
            existing.setReference(request.getReference());

        if (request.getUnits() != null && request.getUnits() >= 0) {
            existing.setUnits(request.getUnits());
        }

        return repository.save(existing);
    }

    public void deleteStock(String id) {
        StockModel stock = getById(id);
        if (stock.getUnits() > 0) throw
                new ResponseStatusException(
                        HttpStatus.CONFLICT, "Stock contains units"
                );
        repository.delete(stock);
    }

    public StockModel decreaseStock(String id, int amount) {
        if (amount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid amount");
        }

        StockModel stock = getById(id);

        if (stock.getUnits() < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough stock");
        }

        stock.setUnits(stock.getUnits() - amount);

        return stock;
    }

    public StockModel registerStock(String locationId, SkuModel sku, int amount){
        if (amount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid amount");
        }
        StockModel stock;
        if (!existsBySkuAndLocation(sku.getId(), locationId)){
            stock = createStockInternal(sku, locationId);
        }
        else
            stock = getBySkuAndLocation(sku.getId(), locationId);

        stock.setUnits(stock.getUnits() + amount);

        return repository.save(stock);
    }

    public List<StockModel> saveAll(List<StockModel> stock){
        return repository.saveAll(stock);
    }
}