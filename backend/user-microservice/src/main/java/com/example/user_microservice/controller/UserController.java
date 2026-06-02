package com.example.user_microservice.controller;

import com.example.user_microservice.client.LocationClient;
import com.example.user_microservice.client.LocationResponse;
import com.example.user_microservice.dto.user.CreateUserRequest;
import com.example.user_microservice.dto.user.UpdateMyProfileRequest;
import com.example.user_microservice.dto.user.UserResponse;
import com.example.user_microservice.mapper.UserMapper;
import com.example.user_microservice.model.user.UserModel;
import com.example.user_microservice.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private LocationClient locationClient;

    @GetMapping("/me")
    public UserResponse getMyProfile() {
        return UserMapper.responseFromModel(service.getMyProfile());
    }

    @GetMapping("/me/detail")
    public UserResponse getMyProfileDetail() {
        UserModel user = service.getMyProfile();
        LocationResponse locationResponse = locationClient.getById(user.getLocationId());
        return UserMapper.responseFromModelDetail(user, locationResponse, service.canDelete(user));
    }

    @PatchMapping("/me/update")
    public UserResponse updateUser(
            @RequestBody UpdateMyProfileRequest request
    ) {

        return UserMapper.responseFromModel(service.updateMyProfile(request));
    }

    @PostMapping("/me/password")
    public String changePassword(
            @RequestParam String newPassword,
            @RequestParam String oldPassword
    ){
        return service.changePassword(oldPassword, newPassword);
    }


}