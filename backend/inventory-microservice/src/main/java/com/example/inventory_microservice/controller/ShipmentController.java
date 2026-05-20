package com.example.inventory_microservice.controller;

import com.example.inventory_microservice.client.LocationClient;
import com.example.inventory_microservice.client.LocationResponse;
import com.example.inventory_microservice.model.shipment.ShipmentModel;
import com.example.inventory_microservice.model.shipment.dto.ShipmentRequest;
import com.example.inventory_microservice.model.shipment.dto.ShipmentResponse;
import com.example.inventory_microservice.model.shipment.enums.ShipmentStatus;
import com.example.inventory_microservice.model.shipment.mapper.ShipmentMapper;
import com.example.inventory_microservice.service.ShipmentService;
import com.example.inventory_microservice.service.searchFilterDto.ShipmentSearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/inventory/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService service;

    @Autowired
    private LocationClient locationClient;

    @GetMapping("/id/{id}")
    public ShipmentResponse getById(@PathVariable String id) {
        ShipmentModel shipmentModel = service.getShipmentById(id);
        LocationResponse destiny = locationClient.getById(shipmentModel.getDestinyLocationId());
        LocationResponse supplier = locationClient.getById(shipmentModel.getSupplierLocationId());

        return ShipmentMapper.responseFromModel(shipmentModel, destiny, supplier);
    }

    @GetMapping("/ref/{ref}")
    public ShipmentResponse getByReference(@PathVariable String ref){
        ShipmentModel shipmentModel = service.getShipmentByReference(ref);
        LocationResponse destiny = locationClient.getById(shipmentModel.getDestinyLocationId());
        LocationResponse supplier = locationClient.getById(shipmentModel.getSupplierLocationId());

        return ShipmentMapper.responseFromModel(shipmentModel, destiny, supplier);
    }

    @GetMapping("/search")
    public List<ShipmentResponse> search(
            @RequestParam(required = false) String supplierLocationId,
            @RequestParam(required = false) String destinyLocationId,
            @RequestParam(required = false) ShipmentStatus status,

            @RequestParam(required = false) LocalDate creationDate,
            @RequestParam(required = false) LocalDate arrivalDate,

            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,

            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDirection
    ) {
        ShipmentSearchFilter filter = new ShipmentSearchFilter();
        filter.setSupplierLocationId(supplierLocationId);
        filter.setDestinyLocationId(destinyLocationId);
        filter.setStatus(status);
        filter.setCreationDate(creationDate);
        filter.setArrivalDate(arrivalDate);
        filter.setPage(page);
        filter.setSize(size);
        filter.setSortBy(sortBy);
        filter.setSortDirection(sortDirection);

        return service.search(filter)
                .stream()
                .map(shipment -> {

                    LocationResponse destiny =
                            locationClient.getById(
                                    shipment.getDestinyLocationId()
                            );

                    LocationResponse supplier =
                            locationClient.getById(
                                    shipment.getSupplierLocationId()
                            );

                    return ShipmentMapper.responseFromModel(
                            shipment,
                            destiny,
                            supplier
                    );
                })
                .toList();
    }

    @GetMapping
    public List<ShipmentResponse> getAll() {

        List<ShipmentModel> shipments = service.getAllShipments();

        List<String> locationIds = shipments.stream()
                .flatMap(s -> Stream.of(
                        s.getDestinyLocationId(),
                        s.getSupplierLocationId()
                ))
                .distinct()
                .toList();

        List<LocationResponse> locations = locationClient.getAllById(locationIds);

        Map<String, LocationResponse> map = locations.stream()
                .collect(Collectors.toMap(LocationResponse::getId, l -> l));

        return shipments.stream()
                .map(s -> ShipmentMapper.responseFromModel(
                        s,
                        map.get(s.getDestinyLocationId()),
                        map.get(s.getSupplierLocationId())
                ))
                .toList();
    }

    @PostMapping
    public ShipmentResponse create(@RequestBody ShipmentRequest request) {
        ShipmentModel shipmentModel = service.createShipment(request);
        LocationResponse destiny = locationClient.getById(shipmentModel.getDestinyLocationId());
        LocationResponse supplier = locationClient.getById(shipmentModel.getSupplierLocationId());

        return ShipmentMapper.responseFromModel(shipmentModel, destiny, supplier);
    }

    @PatchMapping("/{id}/in-progress")
    public void inProgress(@PathVariable String id) {
        service.setShipmentInProccess(id);
    }

    @PatchMapping("/{id}/arrived")
    public void arrived(
            @PathVariable String id,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {
        service.setShipmentArrived(id, date);
    }

    @PatchMapping("/{id}/completed")
    public void completed(@PathVariable String id) {
        service.setShipmentComplete(id);
    }

    @PatchMapping("/{id}/lost")
    public void lost(@PathVariable String id) {
        service.setShipmentLost(id);
    }

    @PatchMapping("/{id}/confirmed")
    public void confirmed(@PathVariable String id) {
        service.setShipmentConfirmed(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteShipment(id);
    }
}