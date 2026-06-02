package com.example.user_microservice.dto.user;

import com.example.user_microservice.client.LocationResponse;
import com.example.user_microservice.model.user.enums.Rank;
import com.example.user_microservice.model.user.enums.Role;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserResponse {
    private String id;

    private String soldierNumber;
    private String firstName;
    private String lastName;
    private String dni;
    private int phoneNumber;
    private Role role;

    private String email;
    private String username;
    private String avatarUrl;
    private LocalDate createdAt;
    private String locationId;
    private Rank rank;

    private boolean active;

    private String tempPassword;

    private LocationResponse locationResponse;

    private boolean canDelete;
}