package com.example.inventory_microservice.model.sku.mapper;

import com.example.inventory_microservice.model.sku.SkuModel;
import com.example.inventory_microservice.model.sku.dto.SkuRequest;
import com.example.inventory_microservice.model.sku.dto.SkuResponse;
import com.example.inventory_microservice.model.stock.dto.StockResponse;

import java.util.List;

public class SkuMapper {
    public static SkuModel modelFromRequest(SkuRequest request, String id, String reference){
        return SkuModel.builder()
                .id(id)
                .itemId(request.getItemId())
                .reference(reference)
                .attributes(request.getAttributes())
                .active(request.getActive())
                .serialized(request.getSerialized())
                .build();
    }

    public static SkuResponse responseFromModelSimple(SkuModel model){
        return SkuResponse.builder()
                .id(model.getId())
                .itemId(model.getItemId())
                .reference(model.getReference())
                .attributes(model.getAttributes())
                .active(model.isActive())
                .serialized(model.isSerialized())
                .build();
    }

    public static List<SkuResponse> responseFromModelListSimple(List<SkuModel> models){
        return models.stream()
                .map(SkuMapper::responseFromModelSimple)
                .toList();
    }
}
