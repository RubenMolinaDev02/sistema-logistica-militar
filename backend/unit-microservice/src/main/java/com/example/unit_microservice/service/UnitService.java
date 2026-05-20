package com.example.unit_microservice.service;

import com.example.unit_microservice.model.unit.UnitModel;
import com.example.unit_microservice.model.unit.dto.UnitNode;
import com.example.unit_microservice.model.unit.dto.UnitRequest;
import com.example.unit_microservice.model.unit.dto.UnitUpdateRequest;
import com.example.unit_microservice.model.unit.mapper.UnitMapper;
import com.example.unit_microservice.repository.UnitRepository;
import com.example.unit_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitService {

    @Autowired
    private UnitRepository repository;

    public UnitModel getUnitById(String id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Unit not found"
                )
        );
    }

    public List<UnitModel> getUnitsByParentId(String id){
        return repository.findAllByParentUnitId(id);
    }

    public UnitModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Unit not found"
                )
        );
    }

    public List<UnitModel> getAllUnit() {
        return repository.findAll();
    }

    public UnitModel saveUnit(UnitRequest unitModel){
        return repository.save(
                UnitMapper.modelFromRequest(
                        unitModel,
                        Utils.generateId("OR-UNIT"),
                        Utils.referenceGenerator("OR-UNIT")
                )
        );
    }

    public UnitModel updateUnit(UnitUpdateRequest unit, String id) {
        UnitModel exists = getUnitById(id);

        if (Utils.validateString(unit.getReference())){
            var existing = repository.readByReference(unit.getReference()).orElse(null);
            if (existing != null && !existing.getId().equals(id)) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Reference already used"
                );
            }
            exists.setReference(unit.getReference());
        }

        if (unit.getParentUnitId() != null){
            getUnitById(unit.getParentUnitId());
            if (isCircular(id, unit.getParentUnitId())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Circular hierarchy not allowed"
                );
            }
            exists.setParentUnitId(unit.getParentUnitId());
        } else {
            exists.setParentUnitId(null);
        }

        if (Utils.validateString(unit.getName())) exists.setName(unit.getName());
        if (unit.getType() != null) exists.setType(unit.getType());

        return repository.save(exists);
    }

    public void deleteUnit(String id) {
        UnitModel unitModel = getUnitById(id);

        if (getUnitsByParentId(id).isEmpty())
            repository.delete(unitModel);
        else throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Unit contains children units"
        );
    }

    public List<UnitModel> getParents(String id) {
        List<UnitModel> parents = new ArrayList<>();
        UnitModel current = getUnitById(id);

        while (current.getParentUnitId() != null) {
            current = getUnitById(current.getParentUnitId());
            parents.add(current);
        }

        return parents;
    }

    public UnitNode getTree(String id) {
        UnitModel root = getUnitById(id);
        return buildTree(root);
    }

    private UnitNode buildTree(UnitModel unit) {
        List<UnitModel> children = getUnitsByParentId(unit.getId());
        return new UnitNode(
                unit,
                children.stream().map(this::buildTree).toList()
        );
    }

    private boolean isCircular(String currentId, String newParentId) {
        String parent = newParentId;

        while (parent != null) {
            if (parent.equals(currentId)) return true;

            UnitModel unit = repository.findById(parent).orElse(null);
            if (unit == null) break;

            parent = unit.getParentUnitId();
        }

        return false;
    }
}