package com.example.user_microservice.mapper;

import com.example.user_microservice.client.LocationResponse;
import com.example.user_microservice.dto.user.CreateUserRequest;
import com.example.user_microservice.dto.user.UserResponse;
import com.example.user_microservice.model.user.UserModel;

import java.time.LocalDate;

public class UserMapper {
    public static UserModel modelFromRequest(CreateUserRequest request, String id){
        return UserModel.builder()
                .id(id)
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dni(request.getDni())
                .phoneNumber(request.getPhoneNumber())
                .username(request.getUsername())
                .avatarUrl(request.getAvatarUrl())
                .createdAt(LocalDate.now())
                .rank(request.getRank())
                .active(request.getActive())
                .soldierNumber(request.getSoldierNumber())
                .locationId(request.getLocationId())
                .role(request.getRole())
                .build();
    }

    public static UserResponse responseFromModel(UserModel user){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .avatarUrl(user.getAvatarUrl())
                .createdAt(user.getCreatedAt())
                .locationId(user.getLocationId())
                .rank(user.getRank())
                .active(user.isActive())
                .soldierNumber(user.getSoldierNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dni(user.getDni())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();
    }

    public static UserResponse responseFromModelDetail(UserModel user, LocationResponse locationResponse, boolean canDelete){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .avatarUrl(user.getAvatarUrl())
                .createdAt(user.getCreatedAt())
                .locationResponse(locationResponse)
                .rank(user.getRank())
                .active(user.isActive())
                .soldierNumber(user.getSoldierNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dni(user.getDni())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .canDelete(canDelete)
                .build();
    }

    public static UserResponse responseFromModelWithTempPassword(UserModel user, String password){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .avatarUrl(user.getAvatarUrl())
                .createdAt(user.getCreatedAt())
                .locationId(user.getLocationId())
                .rank(user.getRank())
                .active(user.isActive())
                .tempPassword(password)
                .build();
    }

}
