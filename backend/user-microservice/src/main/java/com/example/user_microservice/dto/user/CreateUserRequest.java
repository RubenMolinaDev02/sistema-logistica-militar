package com.example.user_microservice.dto.user;

import com.example.user_microservice.model.user.enums.Rank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String dni;
    private int phoneNumber;
    private String avatarUrl;
    private String createdAt;
    private Rank rank;
    private boolean active;
    private String role;
}