package com.example.user_microservice.service;

import com.example.user_microservice.client.LocationClient;
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
import jakarta.ws.rs.NotFoundException;
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
import java.util.Objects;

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
    @Autowired
    private LocationClient locationClient;


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

        if (getUserByUsername(request.getUsername()) != null) throw
            new ResponseStatusException(HttpStatus.CONFLICT);

        if (getUserByDni(request.getDni()) != null) throw
            new ResponseStatusException(HttpStatus.CONFLICT);

        if (getUserByEmail(request.getEmail()) != null) throw
                new ResponseStatusException(HttpStatus.CONFLICT);

        if (getUserByPhone(request.getPhoneNumber()) != null) throw
                new ResponseStatusException(HttpStatus.CONFLICT);

        if (getUserBySoldierNumber(request.getSoldierNumber()) != null) throw
                new ResponseStatusException(HttpStatus.CONFLICT);

        if (locationClient.getById(request.getLocationId()) == null) throw
                new ResponseStatusException(HttpStatus.NOT_FOUND);

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
            UserModel temp = getUserByEmail(request.getEmail());
            if (temp != null && !temp.getId().equals(user.getId())) throw
                    new ResponseStatusException(HttpStatus.CONFLICT);
            user.setEmail(request.getEmail());
        }

        if (request.getFirstName() != null && !request.getFirstName().isBlank()) {
            user.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null && !request.getLastName().isBlank()) {
            user.setLastName(request.getLastName());
        }

        if (request.getPhoneNumber() != null && request.getPhoneNumber() > 0) {
            UserModel temp = getUserByPhone(request.getPhoneNumber());
            if (temp != null && !temp.getId().equals(user.getId())) throw
                    new ResponseStatusException(HttpStatus.CONFLICT);
            user.setPhoneNumber(request.getPhoneNumber());
        }

        if (request.getAvatarUrl() != null && !request.getAvatarUrl().isBlank()) {
            user.setAvatarUrl(request.getAvatarUrl());
        }

        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            UserModel temp = getUserByUsername(request.getUsername());
            if (temp != null && !temp.getId().equals(user.getId())) throw
                    new ResponseStatusException(HttpStatus.CONFLICT);
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
            UserModel temp = getUserByEmail(request.getEmail());
            if (temp != null && !temp.getId().equals(user.getId())) throw
                    new ResponseStatusException(HttpStatus.CONFLICT);
            user.setEmail(request.getEmail());
        }

        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            UserModel temp = getUserByUsername(request.getUsername());
            if (temp != null && !temp.getId().equals(user.getId())) throw
                    new ResponseStatusException(HttpStatus.CONFLICT);
            user.setUsername(request.getUsername());
        }

        if (request.getFirstName() != null && !request.getFirstName().isBlank()) {
            user.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null && !request.getLastName().isBlank()) {
            user.setLastName(request.getLastName());
        }

        if (request.getDni() != null && !request.getDni().isBlank()) {
            UserModel temp = getUserByDni(request.getDni());
            if (temp != null && !temp.getId().equals(user.getId())) throw
                    new ResponseStatusException(HttpStatus.CONFLICT);
            user.setDni(request.getDni());
        }

        if (request.getPhoneNumber() != null && request.getPhoneNumber() > 0) {
            UserModel temp = getUserByPhone(request.getPhoneNumber());
            if (temp != null && !temp.getId().equals(user.getId())) throw
                    new ResponseStatusException(HttpStatus.CONFLICT);
            user.setPhoneNumber(request.getPhoneNumber());
        }

        if (request.getAvatarUrl() != null && !request.getAvatarUrl().isBlank()) {
            user.setAvatarUrl(request.getAvatarUrl());
        }

        if (request.getLocationId() != null && !request.getLocationId().isBlank()) {
            try {
                locationClient.getById(request.getLocationId());
            }catch(Exception e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            user.setLocationId(request.getLocationId());
        }

        if (request.getRank() != null) {
            user.setRank(request.getRank());
        }

        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }

        if (request.getSoldierNumber() != null && !request.getSoldierNumber().isBlank()) {
            UserModel temp = getUserBySoldierNumber(request.getSoldierNumber());
            if (temp != null && !temp.getId().equals(user.getId())) throw
                    new ResponseStatusException(HttpStatus.CONFLICT);
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

    public void deleteUser(String userId, String token) {

        UserModel user = getUserById(userId);

        if (canDelete(user)) {
            userRepository.delete(user);
            keycloakRepository.deleteUser(token, userId);
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "User can only be deleted within the first 7 days after creation"
            );
        }
    }

    public boolean canDelete(UserModel userModel){
        LocalDate limitDate = userModel.getCreatedAt().plusDays(7);

        return LocalDate.now().isBefore(limitDate);
    }

    public UserModel getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(
                         HttpStatus.NOT_FOUND
                 )
        );
    }

    public UserModel getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public UserModel getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public UserModel getUserByPhone(int phone){
        return userRepository.findByPhoneNumber(phone);
    }
    public UserModel getUserBySoldierNumber(String soldierNumber){
        return userRepository.findBySoldierNumber(soldierNumber);
    }
    public UserModel getUserByDni(String dni){
        return userRepository.findByDni(dni);
    }
}