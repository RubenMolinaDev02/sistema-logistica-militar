package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.platform.PlatformModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlatformRepository  extends MongoRepository<PlatformModel, String> {
    @Override
    List<PlatformModel> findAllById(Iterable<String> strings);

    PlatformModel readByReference(String reference);
}
