package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.bayonet.BayonetModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BayonetRepository  extends MongoRepository<BayonetModel, String> {
    Optional<BayonetModel> readByReference(String reference);
}
