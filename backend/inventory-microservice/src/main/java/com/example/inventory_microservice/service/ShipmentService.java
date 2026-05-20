package com.example.inventory_microservice.service;

import com.example.inventory_microservice.client.LocationClient;
import com.example.inventory_microservice.model.batch.batchModel.BatchModel;
import com.example.inventory_microservice.model.shipment.ShipmentModel;
import com.example.inventory_microservice.model.shipment.dto.ShipmentRequest;
import com.example.inventory_microservice.model.shipment.enums.ShipmentStatus;
import com.example.inventory_microservice.model.shipment.mapper.ShipmentMapper;
import com.example.inventory_microservice.model.unit.enums.LogisticalStatus;
import com.example.inventory_microservice.model.unit.enums.UnitStatus;
import com.example.inventory_microservice.repository.ShipmentRepository;
import com.example.inventory_microservice.service.helper.SearchHelpers;
import com.example.inventory_microservice.service.searchFilterDto.ShipmentSearchFilter;
import com.example.inventory_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository repository;

    @Autowired
    private BatchService batchService;

    @Autowired
    private LocationClient locationClient;

    @Autowired
    private ShipmentInventoryService shipmentInventoryService;

    @Autowired
    private UnitTransitionService unitTransitionService;

    @Autowired
    private MongoTemplate mongoTemplate;

    // =========================
    // READ
    // =========================

    public ShipmentModel getShipmentById(String id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Shipment not found"
                )
        );
    }

    public ShipmentModel getShipmentByReference(String reference) {
        return repository.findByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Shipment not found"
                )
        );
    }

    public List<ShipmentModel> getAllShipments() {
        return repository.findAll();
    }

    public List<BatchModel> getShipmentBatches(String shipmentId) {
        return batchService.getByShipmentId(shipmentId);
    }

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "creationDate",
            "arrivalDate",
            "status",
            "reference"
    );

    public List<ShipmentModel> search(ShipmentSearchFilter filter) {

        if (!ALLOWED_SORT_FIELDS.contains(filter.getSortBy())) throw
            new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Sort field not allowed"
            );

        Query query = new Query();

        SearchHelpers.addIfPresent(
                query,
                "supplierLocationId",
                filter.getSupplierLocationId()
        );

        SearchHelpers.addIfPresent(
                query,
                "destinyLocationId",
                filter.getDestinyLocationId()
        );

        SearchHelpers.addIfPresent(
                query,
                "status",
                filter.getStatus()
        );

        SearchHelpers.addIfPresent(
                query,
                "creationDate",
                filter.getCreationDate()
        );

        SearchHelpers.addIfPresent(
                query,
                "arrivalDate",
                filter.getArrivalDate()
        );

        query.with(
                Sort.by(
                        Sort.Direction.fromString(
                                filter.getSortDirection()
                        ),
                        filter.getSortBy()
                )
        );

        query.skip(
                (long) filter.getPage() * filter.getSize()
        );

        query.limit(filter.getSize());

        return mongoTemplate.find(
                query,
                ShipmentModel.class
        );
    }

    // =========================
    // CREATE
    // =========================
    public ShipmentModel createShipment(ShipmentRequest request) {

        if (request.getDestinyId().equals(request.getSupplierId()))
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The supplier location cant be the same as the destiny location"
            );

        if (request.getBatches() == null || request.getBatches().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Shipment must contain at least one batch"
            );
        }

        locationClient.getById(request.getDestinyId());
        locationClient.getById(request.getSupplierId());

        ShipmentModel model = ShipmentMapper.modelFromRequest(
                Utils.generateId("SHPM"),
                Utils.referenceGenerator("SHPM"),
                request.getSupplierId(),
                request.getDestinyId()
        );

        batchService.createBatches(request.getBatches(), model);

        return repository.save(model);
    }

    // =========================
    // STATE TRANSITIONS CORE
    // =========================

    private ShipmentModel updateStatus(
            String id,
            ShipmentStatus expectedCurrent,
            ShipmentStatus newStatus,
            LocalDate arrivalDate,
            String errorMessage
    ) {

        ShipmentModel shipment = getShipmentById(id);

        if (shipment.getStatus() != expectedCurrent) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    errorMessage
            );
        }

        shipment.setStatus(newStatus);

        if (arrivalDate != null) {
            shipment.setArrivalDate(arrivalDate);
        }

        return repository.save(shipment);
    }

    // =========================
    // STATE TRANSITIONS API
    // =========================

    public void setShipmentConfirmed(String id) {

        ShipmentModel shipment = getShipmentById(id);

        List<BatchModel> batches = batchService.getByShipmentId(id);

        List<String> unitIds = batches.stream()
                    .flatMap(batch -> batch.getSerializedItemsIds().stream())
                    .toList();

        shipmentInventoryService.asignToDestinyMultiple(unitIds, shipment.getDestinyLocationId());

        updateStatus(
                id,
                ShipmentStatus.CREATED,
                ShipmentStatus.CONFIRMED,
                null,
                "Shipment is already in proccess"
        );
    }

    public void setShipmentInProccess(String id) {

        List<BatchModel> batches = batchService.getByShipmentId(id);

        List<String> unitIds = batches.stream()
                .flatMap(batch -> batch.getSerializedItemsIds().stream())
                .toList();

        unitTransitionService.changeLogisticalStatus(unitIds, LogisticalStatus.TRANSIT);

        updateStatus(
                id,
                ShipmentStatus.CONFIRMED,
                ShipmentStatus.IN_PROGRESS,
                null,
                "Shipment is not in confirmed"
        );
    }

    public void setShipmentComplete(String id) {

        List<BatchModel> batches = batchService.getByShipmentId(id);

        List<String> unitIds = batches.stream()
                .flatMap(batch -> batch.getSerializedItemsIds().stream())
                .toList();

        unitTransitionService.changeLogisticalStatus(unitIds, LogisticalStatus.IN_STOCK);

        updateStatus(
                id,
                ShipmentStatus.ARRIVED,
                ShipmentStatus.COMPLETED,
                null,
                "Shipment is not arrived"
        );
    }

    public void setShipmentLost(String id) {

        List<BatchModel> batches = batchService.getByShipmentId(id);

        List<String> unitIds = batches.stream()
                .flatMap(batch -> batch.getSerializedItemsIds().stream())
                .toList();

        unitTransitionService.changeStatusMultiple(unitIds, UnitStatus.LOST);
        unitTransitionService.changeLogisticalStatus(unitIds, LogisticalStatus.UNAVAILABLE);

        updateStatus(
                id,
                ShipmentStatus.IN_PROGRESS,
                ShipmentStatus.LOST,
                LocalDate.now(),
                "Shipment is not in progress"
        );
    }

    public void setShipmentArrived(String id, LocalDate arrivalDate) {

        ShipmentModel shipment = getShipmentById(id);

        if (shipment.getStatus() != ShipmentStatus.IN_PROGRESS) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Shipment is not in progress"
            );
        }

        LocalDate finalDate =
                arrivalDate != null
                        ? arrivalDate
                        : LocalDate.now();

        if (finalDate.isAfter(LocalDate.now()) ||
                finalDate.isBefore(shipment.getCreationDate())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Date not valid"
            );
        }

        List<BatchModel> batches =
                batchService.getByShipmentId(id);

        List<String> unitIds = batches.stream()
                .filter(batch -> batch.getSerializedItemsIds() != null)
                .flatMap(batch -> batch.getSerializedItemsIds().stream())
                .toList();

        if (!unitIds.isEmpty()) {

            unitTransitionService.changeLogisticalStatus(
                    unitIds,
                    LogisticalStatus.ARRIVED
            );
        }

        shipment.setStatus(ShipmentStatus.ARRIVED);
        shipment.setArrivalDate(finalDate);

        repository.save(shipment);
    }

    // =========================
    // DELETE
    // =========================

    public void deleteShipment(String id) {

        ShipmentModel shipment = getShipmentById(id);

        if (shipment.getStatus() != ShipmentStatus.CREATED) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Shipment already in progress"
            );
        }
        batchService.revertBatches(batchService.getByShipmentId(shipment.getId()), shipment.getSupplierLocationId());

        repository.delete(shipment);
    }
}