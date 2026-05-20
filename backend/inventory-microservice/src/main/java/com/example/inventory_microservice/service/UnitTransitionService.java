package com.example.inventory_microservice.service;

import com.example.inventory_microservice.model.unit.SerializedUnitModel;
import com.example.inventory_microservice.model.unit.enums.LogisticalStatus;
import com.example.inventory_microservice.model.unit.enums.UnitStatus;
import com.example.inventory_microservice.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class UnitTransitionService {
    @Autowired
    private UnitRepository unitRepository;

    public List<SerializedUnitModel> changeStatusMultiple(
            List<String> unitIds,
            UnitStatus nextStatus
    ) {

        List<SerializedUnitModel> units = unitRepository.findAllById(unitIds);

        units.forEach(unit -> {

            validatePhysicalTransition(
                    unit.getStatus(),
                    nextStatus
            );

            unit.setStatus(nextStatus);
        });

        return unitRepository.saveAll(units);
    }

    private void validatePhysicalTransition(
            UnitStatus current,
            UnitStatus next
    ) {

        boolean valid = switch (current) {

            case USABLE ->

                    next == UnitStatus.DAMAGED
                            || next == UnitStatus.ISSUED
                            || next == UnitStatus.LOST
                            || next == UnitStatus.DESTROYED
                            || next == UnitStatus.DISPOSED;

            case DAMAGED ->

                    next == UnitStatus.REPAIRING
                            || next == UnitStatus.DISPOSED
                            || next == UnitStatus.DESTROYED;

            case REPAIRING ->

                    next == UnitStatus.USABLE
                            || next == UnitStatus.DISPOSED
                            || next == UnitStatus.DAMAGED
                            || next == UnitStatus.DESTROYED;

            case ISSUED ->

                    next == UnitStatus.USABLE
                            || next == UnitStatus.DAMAGED
                            || next == UnitStatus.LOST
                            || next == UnitStatus.DESTROYED;

            case LOST,
                 DESTROYED,
                 DISPOSED -> false;
        };

        if (!valid) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid physical transition: "
                            + current + " -> " + next
            );
        }
    }

    public List<SerializedUnitModel> changeLogisticalStatus(
            List<String> unitIds,
            LogisticalStatus nextStatus
    ) {

        List<SerializedUnitModel> units = unitRepository.findAllById(unitIds);

        units.forEach(unit -> {

            validateTransition(
                    unit.getLogisticalStatus(),
                    nextStatus
            );

            unit.setLogisticalStatus(nextStatus);
        });

        return unitRepository.saveAll(units);
    }

    private void validateTransition(
            LogisticalStatus current,
            LogisticalStatus next
    ) {

        boolean valid = switch (current) {

            case IN_STOCK ->
                    next == LogisticalStatus.RESERVED;

            case RESERVED ->
                    next == LogisticalStatus.TRANSIT
                            || next == LogisticalStatus.IN_STOCK;

            case TRANSIT ->
                    next == LogisticalStatus.ARRIVED;

            case ARRIVED ->
                    next == LogisticalStatus.IN_STOCK;

            default -> false;
        };

        if (!valid) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid logistical transition: "
                            + current + " -> " + next
            );
        }
    }
}
