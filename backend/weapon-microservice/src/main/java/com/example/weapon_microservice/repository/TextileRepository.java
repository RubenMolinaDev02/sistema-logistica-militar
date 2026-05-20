package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.textile.TextileModel;
import com.netflix.appinfo.ApplicationInfoManager;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TextileRepository extends MongoRepository<TextileModel, String> {
    TextileModel findByReference(String reference);

    Optional<TextileModel> readByReference(String reference);
}
