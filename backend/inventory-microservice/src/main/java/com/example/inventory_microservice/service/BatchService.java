package com.example.inventory_microservice.service;

import com.example.inventory_microservice.model.batch.batchItemGeneric.BatchItemGenericModel;
import com.example.inventory_microservice.model.batch.batchItemGeneric.dto.BatchItemGenericRequest;
import com.example.inventory_microservice.model.batch.batchItemGeneric.mapper.BatchItemGenericMapper;
import com.example.inventory_microservice.model.batch.batchModel.BatchModel;
import com.example.inventory_microservice.model.batch.batchModel.dto.BatchRequest;
import com.example.inventory_microservice.model.batch.batchModel.mapper.BatchMapper;
import com.example.inventory_microservice.model.shipment.ShipmentModel;
import com.example.inventory_microservice.model.stock.StockModel;
import com.example.inventory_microservice.model.unit.enums.LogisticalStatus;
import com.example.inventory_microservice.repository.BatchRepository;
import com.example.inventory_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BatchService {

    @Autowired
    private BatchRepository repository;

    @Autowired
    private SkuService skuService;

    @Autowired
    private StockService stockService;

    @Autowired
    private StockLocationService stockLocationService;

    @Autowired
    private UnitTransitionService unitTransitionService;

    @Autowired
    private ShipmentInventoryService shipmentInventoryService;

    
    public List<BatchModel> createBatches(
            List<BatchRequest> batches,
            ShipmentModel shipment
    ) {

        List<BatchModel> results = new ArrayList<>();

        try {

            for (BatchRequest batch : batches) {

                BatchModel created =
                        createBatch(batch, shipment);

                results.add(created);
            }

            return results;

        } catch (Exception e) {

            revertBatches(results, shipment.getSupplierLocationId());

            throw e;
        }
    }

    public void revertBatches(List<BatchModel> batches, String supplierId) {

        for (BatchModel batch : batches) {

            // 1. revert serialized units
            if (batch.getSerializedItemsIds() != null && !batch.getSerializedItemsIds().isEmpty()) {

                unitTransitionService.changeLogisticalStatus(
                        batch.getSerializedItemsIds(),
                        LogisticalStatus.IN_STOCK
                );
            }

            // 2. revert stock (generic items)
            if (batch.getGenericItems() != null) {

                batch.getGenericItems().forEach(item -> {

                    StockModel stock = stockService.getBySkuAndLocation(
                            item.getSkuId(),
                            supplierId
                    );

                    stock.setUnits(stock.getUnits() + item.getQuantity());

                    stockService.saveAll(List.of(stock));
                });
            }
        }

        repository.deleteAll(batches);
    }

    public List<BatchModel> getByShipmentId(String shipmentId) {
        return repository.findAllByShipmentId(shipmentId);
    }

    public BatchModel getById(String id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Batch not found"
                )
        );
    }

    public BatchModel getByReference(String reference) {
        return repository.findByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Batch not found"
                )
        );
    }

    private BatchModel createBatch(
            BatchRequest batch,
            ShipmentModel shipment
    ) {

        List<BatchItemGenericModel> genericItems =
                genericModelsFromRequest(batch.getItems());

        List<StockModel> updatedStocks =
                stockLocationService.processGenericItems(
                        genericItems,
                        shipment
                );

        List<String> serializedUnitIds =
                shipmentInventoryService.reserveSerializedItems(batch);

        BatchModel model = BatchMapper.modelFromRequest(
                Utils.generateId("BATCH"),
                Utils.referenceGenerator("BATCH"),
                shipment.getId(),
                genericItems,
                serializedUnitIds
        );

        if (!serializedUnitIds.isEmpty()) {
            unitTransitionService.changeLogisticalStatus(
                    serializedUnitIds,
                    LogisticalStatus.RESERVED
            );
        }

        if (!updatedStocks.isEmpty()) {
            stockService.saveAll(updatedStocks);
        }

        return repository.save(model);
    }

    public List<BatchItemGenericModel> genericModelsFromRequest(
            List<BatchItemGenericRequest> requests
    ) {

        if (requests == null || requests.isEmpty()) {
            return List.of();
        }

        return requests.stream()
                .map(request -> {

                    String skuId = skuService
                            .getByReference(request.getSkuReference())
                            .getId();

                    return BatchItemGenericMapper.modelFromRequest(
                            request,
                            skuId
                    );
                })
                .toList();
    }
}