package com.example.weapon_microservice.service.armorPlate;

import com.example.weapon_microservice.model.armorPlate.ArmorPlateModel;
import com.example.weapon_microservice.model.armorPlate.dto.ArmorPlateRequest;
import com.example.weapon_microservice.model.armorPlate.dto.ArmorPlateUpdateRequest;
import com.example.weapon_microservice.model.armorPlate.mapper.ArmorPlateMapper;
import com.example.weapon_microservice.repository.ArmorPlateRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class ArmorPlateService {
    @Autowired
    private ArmorPlateRepository repository;
    @Autowired
    private ItemSearchService itemSearchService;

    public ArmorPlateModel getArmorPlateById(String id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Armor plate not found"
                )
        );
    }

    public ArmorPlateModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Armor Plate not found"
                )
        );
    }

    public List<ArmorPlateModel> getAllArmorPlate() {
        return repository.findAll();
    }

    public ArmorPlateModel saveArmorPlate(ArmorPlateRequest armorPlateModel){
        return repository.save(ArmorPlateMapper.modelFromRequest(armorPlateModel, Utils.generateId("PLATE"), Utils.referenceGenerator("PLATE")));
    }

    public ArmorPlateModel updateArmorPlate(ArmorPlateUpdateRequest armorPlate, String id) {
        ArmorPlateModel exists = getArmorPlateById(id);

        itemSearchService.validateReference(armorPlate.getReference());
        if (Utils.validateString(armorPlate.getReference())) exists.setReference(armorPlate.getReference());

        if (Utils.validateString(armorPlate.getName())) exists.setName(armorPlate.getName());
        if (Utils.validateString(armorPlate.getImage())) exists.setImage(armorPlate.getImage());
        if (armorPlate.getStatus() != null) exists.setStatus(armorPlate.getStatus());
        if (armorPlate.getMaterial() != null) exists.setMaterial(armorPlate.getMaterial());
        if (armorPlate.getLevel() != null) exists.setLevel(armorPlate.getLevel());
        if (Utils.validateDouble(exists.getWeight())) exists.setWeight(armorPlate.getWeight());

        return repository.save(exists);
    }

    public void deleteArmorPlate(String id) {
        ArmorPlateModel armorPlateModel = getArmorPlateById(id);
        repository.delete(armorPlateModel);
    }
}
