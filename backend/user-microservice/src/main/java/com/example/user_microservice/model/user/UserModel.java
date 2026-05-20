package com.example.user_microservice.model.user;

import com.example.user_microservice.model.user.enums.Rank;
import com.example.user_microservice.model.user.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Builder
@Getter
@Setter
@Document("users")
public class UserModel {

    @Id
    private String id;

    private String soldierNumber;
    private String firstName;
    private String lastName;
    private String dni;
    private int phoneNumber;
    private String email;
    private String username;
    private String avatarUrl;
    private String createdAt;
    private Rank rank;
    private Role role;

    private boolean active;
}