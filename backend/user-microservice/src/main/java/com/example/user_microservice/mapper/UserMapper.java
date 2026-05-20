package com.example.user_microservice.mapper;

import com.example.user_microservice.dto.user.CreateUserRequest;
import com.example.user_microservice.model.user.UserModel;

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
                .createdAt(request.getCreatedAt())
                .rank(request.getRank())
                .active(request.isActive())
                .build();
    }

}
