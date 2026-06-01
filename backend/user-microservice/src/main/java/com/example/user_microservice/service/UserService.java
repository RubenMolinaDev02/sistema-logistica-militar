package com.example.user_microservice.service;

import com.example.user_microservice.dto.user.CreateUserRequest;
import com.example.user_microservice.dto.user.UpdateMyProfileRequest;
import com.example.user_microservice.dto.user.UpdateUserRequest;
import com.example.user_microservice.dto.user.UserResponse;
import com.example.user_microservice.mapper.UserMapper;
import com.example.user_microservice.model.PageResponse;
import com.example.user_microservice.model.user.UserModel;
import com.example.user_microservice.repository.KeycloakRepository;
import com.example.user_microservice.repository.UserRepository;
import com.example.user_microservice.utils.AuthUtils;
import com.example.user_microservice.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KeycloakRepository keycloakRepository;

    @Autowired
    private UserEventProducer userEventProducer;

    @Autowired
    private UserQueryBuilder userQueryBuilder;


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

    public PageResponse<UserModel> search(SearchRequest request, int page, int size) {
        return userQueryBuilder.search(request, page, size);
    }

    public UserResponse createUser(CreateUserRequest request, String token){
        String id = keycloakRepository.createUser(token, request);

        String password = PasswordGenerator.generate(15);
        System.out.println(password);
        keycloakRepository.updatePassword(token, id, password);

        UserModel user = userRepository.save(UserMapper.modelFromRequest(request, id));

        userEventProducer.sendUserCreatedEvent(
                Map.of(
                        "userId", id,
                        "email", request.getEmail(),
                        "username", request.getUsername()
                )
        );
        return UserMapper.responseFromModelWithTempPassword(user ,password);
    }

    public List<UserModel> getAllUsers() {
        return null;
    }

    public UserModel updateMyProfile(
            UpdateMyProfileRequest request
    ) {

        UserModel user = getMyProfile();

        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            user.setEmail(request.getEmail());
        }

        if (request.getFirstName() != null && !request.getFirstName().isBlank()) {
            user.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null && !request.getLastName().isBlank()) {
            user.setLastName(request.getLastName());
        }

        if (request.getPhoneNumber() != null && request.getPhoneNumber() > 0) {
            user.setPhoneNumber(request.getPhoneNumber());
        }

        if (request.getAvatarUrl() != null && !request.getAvatarUrl().isBlank()) {
            user.setAvatarUrl(request.getAvatarUrl());
        }

        userRepository.save(user);

        return user;
    }

    public UserModel updateUser(
            String userId,
            UpdateUserRequest request
    ) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        Jwt jwt = (Jwt) authentication.getPrincipal();

        UserModel logued = getMyProfile();

        UserModel user = getUserById(userId);

        if (logued.getId().equals(user.getId())) throw new ResponseStatusException(
                HttpStatus.CONFLICT
        );

        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            user.setEmail(request.getEmail());
        }

        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            user.setUsername(request.getUsername());
        }

        if (request.getFirstName() != null && !request.getFirstName().isBlank()) {
            user.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null && !request.getLastName().isBlank()) {
            user.setLastName(request.getLastName());
        }

        if (request.getDni() != null && !request.getDni().isBlank()) {
            user.setDni(request.getDni());
        }

        if (request.getPhoneNumber() != null && request.getPhoneNumber() > 0) {
            user.setPhoneNumber(request.getPhoneNumber());
        }

        if (request.getAvatarUrl() != null && !request.getAvatarUrl().isBlank()) {
            user.setAvatarUrl(request.getAvatarUrl());
        }

        if (request.getLocationId() != null && !request.getLocationId().isBlank()) {
            user.setLocationId(request.getLocationId());
        }

        if (request.getRank() != null) {
            user.setRank(request.getRank());
        }

        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }

        if (request.getSoldierNumber() != null && !request.getSoldierNumber().isBlank()) {
            user.setSoldierNumber(request.getSoldierNumber());
        }

        if (request.getActive() != null) {
            user.setActive(request.getActive());
        }

        keycloakRepository.updateUser(jwt.getTokenValue(), userId, user.getUsername(), user.getEmail(), user.isActive());

        userRepository.save(user);

        return user;
    }

    public String changePassword(String oldPassword, String newPassword){
        UserModel user = getMyProfile();

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        Jwt jwt = (Jwt) authentication.getPrincipal();

        if(keycloakRepository.validatePassword(user.getUsername(), oldPassword)){
            keycloakRepository.updatePassword(jwt.getTokenValue(), user.getId(), newPassword);
            return newPassword;
        }
        throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED
        );
    }

    public void deleteUser(String userId) {

        UserModel user = getUserById(userId);

        LocalDate limitDate = user.getCreatedAt().plusDays(7);

        if (LocalDate.now().isAfter(limitDate)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "User can only be deleted within the first 7 days after creation"
            );
        }

        userRepository.delete(user);
    }

    public UserModel getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(
                         HttpStatus.NOT_FOUND
                 )
        );
    }
}