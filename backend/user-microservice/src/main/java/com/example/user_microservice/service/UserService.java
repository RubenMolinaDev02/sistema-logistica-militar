package com.example.user_microservice.service;

import com.example.user_microservice.dto.user.CreateUserRequest;
import com.example.user_microservice.mapper.UserMapper;
import com.example.user_microservice.model.user.UserModel;
import com.example.user_microservice.repository.KeycloakRepository;
import com.example.user_microservice.repository.UserRepository;
import com.example.user_microservice.utils.AuthUtils;
import com.example.user_microservice.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KeycloakRepository keycloakRepository;

    @Autowired
    private UserEventProducer userEventProducer;


    public UserModel getMyProfile() {
        String userId = AuthUtils.getUserId();

        return userRepository.findById(userId)
                .orElseGet(() -> userRepository.save(
                        UserModel.builder()
                                .id(userId)
                                .email(AuthUtils.getEmail())
                                .build()
                ));
    }

    public void createUser(CreateUserRequest request, String token, String role){
        String id = keycloakRepository.createUser(token, request);

        String password = PasswordGenerator.generate(15);
        System.out.println(password);
        keycloakRepository.updatePassword(token, id, password);

        keycloakRepository.assignRealmRoles(
                token,
                id,
                keycloakRepository.getRoleByName(token, role)
        );

        userRepository.save(UserMapper.modelFromRequest(request, id));

        userEventProducer.sendUserCreatedEvent(
                Map.of(
                        "userId", id,
                        "email", request.getEmail(),
                        "username", request.getUsername()
                )
        );
    }
}