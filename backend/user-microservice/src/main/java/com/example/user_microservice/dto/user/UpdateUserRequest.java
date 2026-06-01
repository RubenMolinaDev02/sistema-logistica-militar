package com.example.user_microservice.dto.user;

import com.example.user_microservice.model.user.enums.Rank;
import com.example.user_microservice.model.user.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String dni;
    private Integer phoneNumber;
    private String avatarUrl;
    private String createdAt;
    private Rank rank;
    private Boolean active;
    private String locationId;
    private String soldierNumber;
    private Role role;
}
