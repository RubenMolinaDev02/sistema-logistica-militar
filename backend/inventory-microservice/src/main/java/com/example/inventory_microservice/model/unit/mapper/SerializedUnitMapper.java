package com.example.inventory_microservice.model.unit.mapper;

import com.example.inventory_microservice.model.unit.SerializedUnitModel;
import com.example.inventory_microservice.model.unit.dto.UnitRequest;
import com.example.inventory_microservice.model.unit.dto.UnitResponse;
import com.example.inventory_microservice.model.unit.enums.LogisticalStatus;
import com.example.inventory_microservice.model.unit.enums.UnitStatus;

import java.util.List;

public class SerializedUnitMapper {
    public static SerializedUnitModel modelFromRequest(
            String serialNumber,
            String id,
            String locationId,
            UnitStatus unitStatus,
            String skuId){
        return SerializedUnitModel.builder()
                .id(id)
                .serialNumber(serialNumber)
                .locationId(locationId)
                .skuId(skuId)
                .status(unitStatus)
                .logisticalStatus(LogisticalStatus.IN_STOCK)
                .build();
    }

    public static UnitResponse responseFromModel(SerializedUnitModel unit) {
        return UnitResponse.builder()
                .id(unit.getId())
                .skuId(unit.getSkuId())
                .locationId(unit.getLocationId())
                .serialNumber(unit.getSerialNumber())
                .status(unit.getStatus())
                .logisticalStatus(unit.getLogisticalStatus())
                .build();
    }

    public static List<UnitResponse> responseFromModelList(List<SerializedUnitModel> units){
        return units.stream()
                .map(SerializedUnitMapper::responseFromModel)
                .toList();
    }
}
