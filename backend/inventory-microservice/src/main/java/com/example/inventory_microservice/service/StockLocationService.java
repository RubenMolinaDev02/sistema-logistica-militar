package com.example.inventory_microservice.service;

import com.example.inventory_microservice.client.LocationClient;
import com.example.inventory_microservice.client.LocationResponse;
import com.example.inventory_microservice.model.batch.batchItemGeneric.BatchItemGenericModel;
import com.example.inventory_microservice.model.shipment.ShipmentModel;
import com.example.inventory_microservice.model.sku.SkuModel;
import com.example.inventory_microservice.model.stock.StockModel;
import com.example.inventory_microservice.model.unit.SerializedUnitModel;
import com.example.inventory_microservice.model.unit.enums.LogisticalStatus;
import com.example.inventory_microservice.model.unit.enums.UnitStatus;
import com.example.inventory_microservice.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockLocationService {
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private SkuService skuService;
    @Autowired
    private LocationClient locationClient;
    @Autowired
    private StockService stockService;


    public List<StockModel> processGenericItems(
            List<BatchItemGenericModel> items,
            ShipmentModel shipment
    ) {

        List<StockModel> updatedStocks = new ArrayList<>();

        for (BatchItemGenericModel item : items) {

            StockModel originStock = stockService.getBySkuAndLocation(
                    item.getSkuId(),
                    shipment.getSupplierLocationId()
            );

            if (originStock.getUnits() < item.getQuantity()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Not enough units in stock for SKU: " + item.getSkuId()
                );
            }

            updatedStocks.add(
                    stockService.decreaseStock(originStock.getId(), item.getQuantity())
            );
        }

        return updatedStocks;
    }

    public List<SerializedUnitModel> getUsableUnits(String skuId, String locationId){
        return unitRepository.findAllBySkuIdAndLocationIdAndStatusInAndLogisticalStatusIn(
                skuId,
                locationId,
                List.of(UnitStatus.USABLE),
                List.of(LogisticalStatus.IN_STOCK)
        );
    }

    public List<SerializedUnitModel> getShipableUnits(String skuId, String locationId){
        return unitRepository.findAllBySkuIdAndLocationIdAndStatusInAndLogisticalStatusIn(
                skuId,
                locationId,
                List.of(UnitStatus.USABLE, UnitStatus.DAMAGED),
                List.of(LogisticalStatus.IN_STOCK)
        );
    }

    public List<SerializedUnitModel> getRandomUnitsBySkuAndLocation(int amount, String skuId, String locationId){
        List<SerializedUnitModel> units = getUsableUnits(skuId, locationId);
        if (units.size() < amount) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Not enough units"
        );
        return units.subList(0, amount);
    }

    public List<SerializedUnitModel> getSerializedStockInLocation(String skuRef, String locationRef){
        SkuModel sku = skuService.getByReference(skuRef);
        LocationResponse locationResponse = locationClient.getByReference(locationRef);

        if (!sku.isSerialized()) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Only serialized sku can call this method"
        );

        return getBySkuAndLocation(sku.getId(), locationResponse.getId());
    }

    private List<SerializedUnitModel> getBySkuAndLocation(String skuId, String locationId){
        return unitRepository.findAllBySkuIdAndLocationId(skuId, locationId);
    }
}
