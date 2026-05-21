package com.example.weapon_microservice.service.textile;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.textile.TextileModel;
import com.example.weapon_microservice.model.textile.dto.TextileRequest;
import com.example.weapon_microservice.model.textile.dto.TextileUpdateRequest;
import com.example.weapon_microservice.model.textile.mapper.TextileMapper;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.repository.TextileRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class TextileService {
    @Autowired
    private TextileRepository repository;
    @Autowired
    private ItemSearchService itemSearchService;
    @Autowired
    private TextileQueryBuilder queryBuilder;

    public TextileModel getTextileById(String id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Textile Item not found"
                )
        );
    }

    public TextileModel getByReference(String reference){
        return repository.findByReference(reference);
    }

    public List<TextileModel> getAllTextile() {
        return repository.findAll();
    }

    public TextileModel saveTextile(TextileRequest textileModel){
        return repository.save(TextileMapper.modelFromRequest(textileModel, Utils.generateId("TEX"), Utils.referenceGenerator("TEX")));
    }

    public TextileModel updateTextile(TextileUpdateRequest textile, String id) {
        TextileModel exists = getTextileById(id);

        itemSearchService.validateReference(textile.getReference());

        if (Utils.validateString(textile.getName())) exists.setName(textile.getName());
        if (textile.getReference() != null) exists.setReference(textile.getReference());
        if (Utils.validateString(textile.getImage())) exists.setImage(textile.getImage());
        if (textile.getType() != null) exists.setType(textile.getType());


        return repository.save(exists);
    }

    public void deleteTextile(String id) {
        TextileModel textileModel = getTextileById(id);
        repository.delete(textileModel);
    }

    public PageResponse<TextileModel> search(SearchRequest request, int page, int size) {
        return queryBuilder.search(request, page, size);
    }
}
