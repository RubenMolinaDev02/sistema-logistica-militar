package com.example.weapon_microservice.service.miscItem;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.miscItems.MiscItemModel;
import com.example.weapon_microservice.model.miscItems.dto.MiscItemRequest;
import com.example.weapon_microservice.model.miscItems.dto.MiscItemUpdateRequest;
import com.example.weapon_microservice.model.miscItems.mapper.MiscItemMapper;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.repository.MiscItemRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class MiscItemService {
    @Autowired
    private MiscItemRepository repository;
    @Autowired
    private ItemSearchService itemSearchService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MiscItemQueryBuilder miscItemQueryBuilder;

    public MiscItemModel getMiscItemById(String id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Item not found"
                )
        );
    }

    public MiscItemModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Item not found"
                )
        );
    }

    public List<MiscItemModel> getAllMiscItem() {
        return repository.findAll();
    }

    public MiscItemModel saveMiscItem(MiscItemRequest miscItemModel){
        return repository.save(MiscItemMapper.modelFromRequest(miscItemModel, Utils.generateId("MISC"), Utils.referenceGenerator("MISC")));
    }

    public PageResponse<MiscItemModel> search(SearchRequest request, int page, int size) {
        return miscItemQueryBuilder.search(request, page, size);
    }

    public MiscItemModel updateMiscItem(MiscItemUpdateRequest miscItem, String id) {
        MiscItemModel exists = getMiscItemById(id);

        itemSearchService.validateReference(miscItem.getReference(), exists.getReference());

        if (Utils.validateString(miscItem.getName())) exists.setName(miscItem.getName());
        if (Utils.validateString(miscItem.getReference())) exists.setReference(miscItem.getReference());
        if (Utils.validateString(miscItem.getImage())) exists.setImage(miscItem.getImage());
        if (miscItem.getType() != null) exists.setType(miscItem.getType());

        return repository.save(exists);
    }

    public void deleteMiscItem(String id) {
        MiscItemModel miscItemModel = getMiscItemById(id);
        repository.delete(miscItemModel);
    }
}
