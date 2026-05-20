package com.example.inventory_microservice.model.stock.mapper;

import com.example.inventory_microservice.model.stock.StockModel;
import com.example.inventory_microservice.model.stock.dto.StockRequest;
import com.example.inventory_microservice.model.stock.dto.StockResponse;

import java.util.List;

public class StockMapper {
    public static StockModel modelFromRequest(StockRequest request, String id, String skuId, String locationId, String reference){
        return StockModel.builder()
                .id(id)
                .skuId(skuId)
                .locationId(locationId)
                .units(request.getUnits())
                .reference(reference)
                .build();
    }

    public static StockResponse responseFromModelSimple(StockModel model){
        return StockResponse.builder()
                .id(model.getId())
                .skuId(model.getSkuId())
                .locationId(model.getLocationId())
                .units(model.getUnits())
                .reference(model.getReference())
                .build();
    }

    public static List<StockResponse> responseFromModelListSimple(List<StockModel> models){
        return models.stream()
                .map(StockMapper::responseFromModelSimple)
                .toList();
    }
}
