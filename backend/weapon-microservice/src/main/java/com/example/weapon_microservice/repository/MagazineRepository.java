package com.example.weapon_microservice.repository;

import com.example.weapon_microservice.model.magazine.MagazineModel;
import com.example.weapon_microservice.service.magazine.MagazineService;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MagazineRepository  extends MongoRepository<MagazineModel, String> {
    Optional<MagazineModel> readByReference(String reference);
}
