package com.example.weapon_microservice.service.holster;

import com.example.weapon_microservice.model.holster.HolsterModel;
import com.example.weapon_microservice.model.holster.dto.HolsterRequest;
import com.example.weapon_microservice.model.holster.dto.HolsterUpdateRequest;
import com.example.weapon_microservice.model.holster.mapper.HolsterMapper;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.repository.HolsterRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.weapon.WeaponService;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class HolsterService {
    @Autowired
    private HolsterRepository repository;
    @Autowired
    private WeaponService weaponService;
    @Autowired
    private ItemSearchService itemSearchService;

    public HolsterModel getHolsterById(String id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Holster not found"
                )
        );
    }

    public HolsterModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Holster not found"
                )
        );
    }

    public List<WeaponModel> getWeaponsById(List<String> id){
        return weaponService.findAllByIds(id);
    }

    public List<HolsterModel> getAllHolster() {
        return repository.findAll();
    }

    public HolsterModel saveHolster(HolsterRequest holsterModel){
        holsterModel.setCompatibleWeaponIds(
                filterExistingWeaponIds(holsterModel.getCompatibleWeaponIds())
        );
        return repository.save(HolsterMapper.modelFromRequest(holsterModel, Utils.generateId("HLST"), Utils.referenceGenerator("HLST")));
    }

    public HolsterModel updateHolster(HolsterUpdateRequest holster, String id) {
        HolsterModel exists = getHolsterById(id);

        itemSearchService.validateReference(holster.getReference());
        if(Utils.validateString(holster.getReference())) exists.setReference(holster.getReference());

        if (Utils.validateString(holster.getName())) exists.setName(holster.getName());
        if (Utils.validateString(holster.getImage())) exists.setImage(holster.getImage());
        if (holster.getType() != null) exists.setType(holster.getType());
        if (holster.getUniversal() != null) exists.setUniversal(holster.getUniversal());

        if (holster.getCompatibleWeaponIds() != null) exists.setCompatibleWeaponIds(
                filterExistingWeaponIds(holster.getCompatibleWeaponIds())
        );

        return repository.save(exists);
    }

    public void deleteHolster(String id) {
        HolsterModel holsterModel = getHolsterById(id);
        repository.delete(holsterModel);
    }

    private List<String> filterExistingWeaponIds(List<String> ids) {

        List<WeaponModel> weapons = getWeaponsById(ids);

        return weapons.stream()
                .map(WeaponModel::getId)
                .toList();
    }
}
