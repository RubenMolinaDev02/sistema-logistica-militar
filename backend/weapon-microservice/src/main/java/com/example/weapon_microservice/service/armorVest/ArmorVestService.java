package com.example.weapon_microservice.service.armorVest;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.armorVest.ArmorVestModel;
import com.example.weapon_microservice.model.armorVest.dto.ArmorVestRequest;
import com.example.weapon_microservice.model.armorVest.dto.ArmorVestUpdateRequest;
import com.example.weapon_microservice.model.armorVest.mapper.ArmorVestMapper;
import com.example.weapon_microservice.model.attachment.AttachmentModel;
import com.example.weapon_microservice.repository.ArmorVestRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ArmorVestService {
    @Autowired
    private ArmorVestRepository repository;
    @Autowired
    private ItemSearchService itemSearchService;
    @Autowired
    private ArmorVestQueryBuilder queryBuilder;

    public ArmorVestModel getArmorVestById(String id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Armor vest not found"
                )
        );
    }

    public ArmorVestModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Armor vest not found"
                )
        );
    }

    public List<ArmorVestModel> getAllArmorVest() {
        return repository.findAll();
    }

    public ArmorVestModel saveArmorVest(ArmorVestRequest armorVestModel){
        return repository.save(ArmorVestMapper.modelFromRequest(armorVestModel, Utils.generateId("VEST"), Utils.referenceGenerator("VEST")));
    }

    public ArmorVestModel updateArmorVest(ArmorVestUpdateRequest armorVest, String id) {
        ArmorVestModel exists = getArmorVestById(id);

        itemSearchService.validateReference(armorVest.getReference());
        if (Utils.validateString(armorVest.getReference())) exists.setReference(armorVest.getReference());

        if (armorVest.getStatus() != null) exists.setStatus(armorVest.getStatus());
        if (Utils.validateString(armorVest.getName())) exists.setName(armorVest.getName());
        if (Utils.validateString(armorVest.getImage())) exists.setImage(armorVest.getImage());
        if (armorVest.getSoftArmor() != null) exists.setSoftArmor(armorVest.getSoftArmor());
        if (armorVest.getCompatibleWithPlates() != null) exists.setCompatibleWithPlates(armorVest.getCompatibleWithPlates());

        return repository.save(exists);
    }

    public void deleteArmorVest(String id) {
        ArmorVestModel armorVestModel = getArmorVestById(id);
        repository.delete(armorVestModel);
    }

    public PageResponse<ArmorVestModel> search(SearchRequest request, int page, int size) {
        return queryBuilder.search(request, page, size);
    }
}
