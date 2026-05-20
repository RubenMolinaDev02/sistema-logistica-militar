package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.caliber.CaliberModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CaliberRepository  extends MongoRepository<CaliberModel, String> {
    List<CaliberModel> findByName(String name);

    CaliberModel readByReference(String reference);
}
