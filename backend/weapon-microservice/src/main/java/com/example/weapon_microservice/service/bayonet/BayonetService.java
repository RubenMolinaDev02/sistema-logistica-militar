package com.example.weapon_microservice.service.bayonet;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.bayonet.BayonetModel;
import com.example.weapon_microservice.model.bayonet.dto.BayonetRequest;
import com.example.weapon_microservice.model.bayonet.dto.BayonetUpdateRequest;
import com.example.weapon_microservice.model.bayonet.mapper.BayonetMapper;
import com.example.weapon_microservice.model.caliber.CaliberModel;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.repository.BayonetRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.service.weapon.WeaponService;
import com.example.weapon_microservice.utils.Utils;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BayonetService {
    @Autowired
    private BayonetRepository repository;
    @Autowired
    private WeaponService weaponService;
    @Autowired
    private ItemSearchService itemSearchService;
    @Autowired
    private BayonetQueryBuilder queryBuilder;

    public BayonetModel getBayonetById(String id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Bayonet not found"
                )
        );
    }

    public BayonetModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Bayonet not found"
                )
        );
    }

    public List<BayonetModel> getAllBayonets() {
        return repository.findAll();
    }

    public BayonetModel saveBayonet(BayonetRequest model){
        model.setCompatibleWeaponsIds(filterExistingWeaponIds(model.getCompatibleWeaponsIds()));
        return repository.save(BayonetMapper.modelFromRequest(model, Utils.generateId("BYNT"), Utils.referenceGenerator("BYNT")));
    }

    public List<WeaponModel> getWeaponsByIds(@NotNull List<String> compatibleWeaponsIds) {
        return weaponService.findAllByIds(compatibleWeaponsIds);
    }

    public BayonetModel updateBayonet(BayonetUpdateRequest bayonet, String id) {
        BayonetModel exists = getBayonetById(id);

        itemSearchService.validateReference(bayonet.getReference(), exists.getReference());
        if (Utils.validateString(bayonet.getReference())) exists.setReference(bayonet.getReference());

        if (Utils.validateString(bayonet.getName())) exists.setName(bayonet.getName());
        if (bayonet.getType() != null) exists.setType(bayonet.getType());
        if (Utils.validateString(bayonet.getImage())) exists.setImage(bayonet.getImage());
        if (Utils.validateDouble(bayonet.getBladeLength())) exists.setBladeLength(bayonet.getBladeLength());
        if (bayonet.getCompatibleWeaponsIds() != null) exists.setCompatibleWeaponsIds(filterExistingWeaponIds(bayonet.getCompatibleWeaponsIds()));

        return repository.save(exists);
    }

    public void deleteBayonet(String id) {
        BayonetModel model = getBayonetById(id);

        repository.delete(model);
    }

    private List<String> filterExistingWeaponIds(List<String> ids) {

        List<WeaponModel> weaponModels = getWeaponsByIds(ids);

        return weaponModels.stream()
                .map(WeaponModel::getId)
                .toList();
    }

    public PageResponse<BayonetModel> search(SearchRequest request, int page, int size) {
        return queryBuilder.search(request, page, size);
    }
}
