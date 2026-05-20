package com.example.inventory_microservice.service;

import com.example.inventory_microservice.client.LocationClient;
import com.example.inventory_microservice.model.unit.SerializedUnitModel;
import com.example.inventory_microservice.model.unit.dto.UnitRequest;
import com.example.inventory_microservice.model.unit.enums.UnitStatus;
import com.example.inventory_microservice.model.unit.mapper.SerializedUnitMapper;
import com.example.inventory_microservice.repository.UnitRepository;
import com.example.inventory_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private LocationClient locationClient;

    public SerializedUnitModel getById(String id){
        return unitRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Serialized Unit not found"
                )
        );
    }

    public List<SerializedUnitModel> getAllById(List<String> ids){
        return unitRepository.findAllById(ids);
    }

    public SerializedUnitModel getBySerialNumber(String serial){
        return unitRepository.findBySerialNumber(serial).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Serialized Unit not found"
                )
        );
    }

    public List<SerializedUnitModel> getBySkuId(String skuId){
        return unitRepository.findAllBySkuId(skuId);
    }

    public List<SerializedUnitModel> getAllBySerialNumbers(List<String> serials){
        return unitRepository.findAllBySerialNumberIn((serials));
    }

    public List<SerializedUnitModel> createUnits(UnitRequest request){

        locationClient.getById(request.getLocationId());

        boolean exists = request.getSerialNumbers().stream()
                .anyMatch(unitRepository::existsBySerialNumber);

        if (exists) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "One or more serial numbers already exist"
            );
        }
        List<SerializedUnitModel> models =
                request.getSerialNumbers().stream()
                .map(serial ->
                        createUnit(
                                serial,
                                request.getLocationId(),
                                request.getSkuId(),
                                request.getStatus()
                        )
                )
                .toList();

        return unitRepository.saveAll(models);
    }

    public SerializedUnitModel createUnit(String serialNumber, String locationId, String skuId, UnitStatus status){
        return SerializedUnitMapper.modelFromRequest(
                serialNumber,
                Utils.generateId("ST-UNIT"),
                locationId,
                status,
                skuId
        );
    }

    public void validateAllSerialsExist(
            List<String> requestedSerials,
            List<SerializedUnitModel> foundUnits
    ) {

        Set<String> foundSerials = foundUnits.stream()
                .map(SerializedUnitModel::getSerialNumber)
                .collect(Collectors.toSet());

        List<String> missingSerials = requestedSerials.stream()
                .filter(serial -> !foundSerials.contains(serial))
                .toList();

        if (!missingSerials.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Some serial numbers do not exist: " + missingSerials
            );
        }
    }


}
