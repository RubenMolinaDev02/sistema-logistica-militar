package com.example.user_microservice.dto.user;

import com.example.user_microservice.model.user.enums.Rank;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserResponse {
    private String id;

    private String email;
    private String username;
    private String avatarUrl;
    private LocalDate createdAt;
    private String locationId;
    private Rank rank;
    private String tempPassword;

    private boolean active;
}