package com.example.weapon_microservice.service.ammo;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.ammo.AmmoModel;
import com.example.weapon_microservice.model.ammo.dto.AmmoRequest;
import com.example.weapon_microservice.model.ammo.dto.AmmoUpdateRequest;
import com.example.weapon_microservice.model.ammo.mapper.AmmoMapper;
import com.example.weapon_microservice.model.caliber.CaliberModel;
import com.example.weapon_microservice.model.handguard.HandguardModel;
import com.example.weapon_microservice.repository.AmmoRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.caliber.CaliberService;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AmmoService {
    @Autowired
    private AmmoRepository repository;
    @Autowired
    private CaliberService caliberService;
    @Autowired
    private ItemSearchService itemSearchService;
    @Autowired
    private AmmoQueryBuilder queryBuilder;

    public AmmoModel getAmmoById(String id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Ammo not found"
                )
        );
    }

    public AmmoModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Ammo not found"
                )
        );
    }

    public CaliberModel getCaliberById(String id){
        return caliberService.getCaliberById(id);
    }

    public List<AmmoModel> getAllAmmo() {
        return repository.findAll();
    }

    public AmmoModel saveAmmo(AmmoRequest ammoModel){
        return repository.save(AmmoMapper.modelFromRequest(ammoModel, Utils.generateId("AMMO"), Utils.referenceGenerator("AMMO")));
    }

    public List<AmmoModel> getAmmoByCaliberId(String caliberId){
        return repository.findAllByCaliberId(caliberId);
    }

    public AmmoModel updateAmmo(AmmoUpdateRequest ammo, String id) {
        AmmoModel exists = getAmmoById(id);

        itemSearchService.validateReference(ammo.getReference());
        if (Utils.validateString(ammo.getReference())) exists.setReference(ammo.getReference());

        if (Utils.validateString(ammo.getName())) exists.setName(ammo.getName());
        if (ammo.getType() != null) exists.setType(ammo.getType());

        if (Utils.validateString(ammo.getCaliberId())){
            getCaliberById(ammo.getCaliberId());
            exists.setCaliberId(ammo.getCaliberId());
        }

        return repository.save(exists);
    }

    public void deleteAmmo(String id) {
        AmmoModel ammoModel = getAmmoById(id);
        repository.delete(ammoModel);
    }

    public PageResponse<AmmoModel> search(SearchRequest request, int page, int size) {
        return queryBuilder.search(request, page, size);
    }
}
