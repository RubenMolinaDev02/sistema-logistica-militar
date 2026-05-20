package com.example.user_microservice.repository;

import com.example.user_microservice.model.user.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {
}
