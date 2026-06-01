package com.example.user_microservice.dto.user;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMyProfileRequest {

    @Email(message = "Invalid email format")
    private String email;

    private String firstName;

    private String lastName;

    private Integer phoneNumber;

    private String avatarUrl;
}