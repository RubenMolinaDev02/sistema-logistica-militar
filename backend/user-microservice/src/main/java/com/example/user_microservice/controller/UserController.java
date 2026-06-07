package com.example.user_microservice.controller;

import com.example.user_microservice.client.LocationClient;
import com.example.user_microservice.client.LocationResponse;
import com.example.user_microservice.dto.user.CreateUserRequest;
import com.example.user_microservice.dto.user.UpdateMyProfileRequest;
import com.example.user_microservice.dto.user.UserResponse;
import com.example.user_microservice.mapper.UserMapper;
import com.example.user_microservice.model.user.UserModel;
import com.example.user_microservice.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private LocationClient locationClient;

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping("/me")
    public UserResponse getMyProfile() {
        return UserMapper.responseFromModel(service.getMyProfile());
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @GetMapping("/me/detail")
    public UserResponse getMyProfileDetail() {
        UserModel user = service.getMyProfile();
        LocationResponse locationResponse = locationClient.getById(user.getLocationId());
        return UserMapper.responseFromModelDetail(user, locationResponse, service.canDelete(user));
    }

    @PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @PatchMapping("/me/update")
    public UserResponse updateUser(
            @RequestBody UpdateMyProfileRequest request
    ) {

        return UserMapper.responseFromModel(service.updateMyProfile(request));
    }

    /*@PreAuthorize("hasAnyRole(@roleProperties.admin, @roleProperties.manager, @roleProperties.publicAccess, @roleProperties.soldier)")
    @PostMapping("/me/password")
    public String changePassword(
            @RequestParam String newPassword,
            @RequestParam String oldPassword
    ){
        return service.changePassword(oldPassword, newPassword);
    }*/


}