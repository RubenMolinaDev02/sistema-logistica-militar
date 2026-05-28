package com.example.weapon_microservice.service.nvg;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.nvg.NvgModel;
import com.example.weapon_microservice.model.nvg.dto.NvgRequest;
import com.example.weapon_microservice.model.nvg.dto.NvgUpdateRequest;
import com.example.weapon_microservice.model.nvg.mapper.NvgMapper;
import com.example.weapon_microservice.model.optic.OpticModel;
import com.example.weapon_microservice.repository.NvgRepository;
import com.example.weapon_microservice.service.ItemSearchService;
import com.example.weapon_microservice.service.SearchRequest;
import com.example.weapon_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class NvgService {
    @Autowired
    private NvgRepository repository;
    @Autowired
    private ItemSearchService itemSearchService;
    @Autowired
    private NvgQueryBuilder queryBuilder;

    public NvgModel getNvgById(String id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Night vision googles not found"
                )
        );
    }

    public NvgModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Night vision googles not found"
                )
        );
    }

    public List<NvgModel> getAllNvg() {
        return repository.findAll();
    }

    public NvgModel saveNvg(NvgRequest nvgModel){
        return repository.save(NvgMapper.modelFromRequest(nvgModel, Utils.generateId("NVG"), Utils.referenceGenerator("NVG")));
    }

    public NvgModel updateNvg(NvgUpdateRequest nvg, String id) {
        NvgModel exists = getNvgById(id);

        itemSearchService.validateReference(nvg.getReference(), exists.getReference());

        if (Utils.validateString(nvg.getName())) exists.setName(nvg.getName());
        if (Utils.validateString(nvg.getReference())) exists.setReference(nvg.getReference());
        if (Utils.validateString(nvg.getImage())) exists.setImage(nvg.getImage());
        if (Utils.validateInt(nvg.getRange())) exists.setRange(nvg.getRange());
        if (nvg.getGeneration() != null) exists.setGeneration(nvg.getGeneration());

        return repository.save(exists);
    }

    public void deleteNvg(String id) {
        NvgModel nvgModel = getNvgById(id);
        repository.delete(nvgModel);
    }

    public PageResponse<NvgModel> search(SearchRequest request, int page, int size) {
        return queryBuilder.search(request, page, size);
    }
}
