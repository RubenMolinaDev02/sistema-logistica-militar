package com.example.inventory_microservice.service;

import com.example.inventory_microservice.model.sku.SkuModel;
import com.example.inventory_microservice.model.sku.dto.SkuRequest;
import com.example.inventory_microservice.model.sku.dto.SkuUpdateRequest;
import com.example.inventory_microservice.model.sku.mapper.SkuMapper;
import com.example.inventory_microservice.repository.SkuRepository;
import com.example.inventory_microservice.service.helper.SearchHelpers;
import com.example.inventory_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SkuService {
    @Autowired
    private SkuRepository repository;

    @Autowired
    private StockService stockService;

    @Autowired
    private UnitService unitService;

    public SkuModel getById(String id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sku not found")
        );
    }

    public SkuModel getByReference(String ref){
        return repository.readByReference(ref).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sku not found")
        );
    }

    public List<SkuModel> getByItemId(String itemId){
        return repository.findAllByItemId(itemId);
    }



    public List<SkuModel> getAll() {
        return repository.findAll();
    }

    public int getStockGeneral(String skuRef){
        SkuModel sku = getByReference(skuRef);

        if (sku.isSerialized()){
            return unitService.getBySkuId(sku.getId()).size();
        }
        return stockService.getUnserializedStockGeneral(sku.getId());
    }

    public SkuModel createSku(SkuRequest request) {

        SkuModel model = SkuMapper.modelFromRequest(
                request,
                Utils.generateId("SKU"),
                Utils.referenceGenerator("SKU")
        );

        return repository.save(model);
    }

    public SkuModel updateSku(String id, SkuUpdateRequest request) {

        SkuModel existing = getById(id);

        if (request.getReference() != null) {

            if (repository.existsByReference(request.getReference())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Reference already exists"
                );
            }

            existing.setReference(request.getReference());
        }

        if (request.getAttributes() != null)
            existing.setAttributes(request.getAttributes());

        if (request.getActive() != null)
            existing.setActive(request.getActive());

        return repository.save(existing);
    }

    public void deleteSku(String id) {
        SkuModel sku = getById(id);
        if(!stockService.getBySkuId(id).isEmpty()) throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Sku still contains stock"
        );
        repository.delete(sku);
    }
}
