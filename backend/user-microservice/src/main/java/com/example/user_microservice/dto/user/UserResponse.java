package com.example.user_microservice.dto.user;

import com.example.user_microservice.model.user.enums.Rank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private String id;

    private String email;
    private String username;
    private String avatarUrl;
    private String createdAt;
    private Rank rank;

    private boolean active;
}