package com.example.location_microservice.service;

import com.example.location_microservice.model.location.LocationModel;
import com.example.location_microservice.model.location.dto.LocationRequest;
import com.example.location_microservice.model.location.dto.LocationUpdateRequest;
import com.example.location_microservice.model.location.enums.LocationStatus;
import com.example.location_microservice.model.location.mapper.LocationMapper;
import com.example.location_microservice.repository.LocationRepository;
import com.example.location_microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    LocationRepository repository;

    public LocationModel getLocationById(String id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Location not found"
                )
        );
    }

    public LocationModel getByReference(String reference){
        return repository.readByReference(reference).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Location not found"
                )
        );
    }

    public List<LocationModel> getAllByIds(List<String> ids){
        return repository.findAllById(ids);
    }

    public List<LocationModel> getAllLocation() {
        return repository.findAll();
    }

    public LocationModel saveLocation(LocationRequest locationModel){
        LocationModel model = LocationMapper.modelFromRequest(
                locationModel,
                Utils.generateId("LCTN"),
                Utils.referenceGenerator("LCTN")
        );

        if (model.getStatus() == null) {
            model.setStatus(LocationStatus.ACTIVE);
        }

        return repository.save(model);
    }

    public LocationModel updateLocation(LocationUpdateRequest location, String id) {
        LocationModel exists = getLocationById(id);

        if (Utils.validateString(location.getReference())) {
            repository.readByReference(location.getReference()).ifPresent(found -> {
                if (!found.getId().equals(id)) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Reference already in use"
                    );
                }
            });
            exists.setReference(location.getReference());
        }

        if (Utils.validateString(location.getName())) exists.setName(location.getName());
        if (location.getType() != null) exists.setType(location.getType());
        if (Utils.validateString(location.getCountry())) exists.setCountry(location.getCountry());
        if (Utils.validateInt(location.getMaxTroops())) exists.setMaxTroops(location.getMaxTroops());
        if (location.getStatus() != null) exists.setStatus(location.getStatus());

        return repository.save(exists);
    }

    public void deleteLocation(String id) {
        LocationModel locationModel = getLocationById(id);
        repository.delete(locationModel);
    }
}