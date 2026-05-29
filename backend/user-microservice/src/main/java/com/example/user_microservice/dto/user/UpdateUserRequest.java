package com.example.user_microservice.dto.user;

import com.example.user_microservice.model.user.enums.Rank;
import com.example.user_microservice.model.user.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "DNI is required")
    private String dni;

    @Positive(message = "Phone number must be valid")
    private int phoneNumber;

    @NotBlank(message = "Avatar URL is required")
    private String avatarUrl;

    @NotBlank(message = "Created date is required")
    private String createdAt;

    @NotNull(message = "Rank is required")
    private Rank rank;

    @NotNull(message = "Active status is required")
    private Boolean active;

    @NotBlank(message = "Location ID is required")
    private String locationId;

    @NotBlank(message = "Soldier number is required")
    private String soldierNumber;

    @NotNull(message = "Role is required")
    private Role role;
}
