package com.example.user_microservice.controller;

import com.example.user_microservice.client.LocationClient;
import com.example.user_microservice.client.LocationResponse;
import com.example.user_microservice.dto.role.RoleMapping;
import com.example.user_microservice.dto.user.CreateUserRequest;
import com.example.user_microservice.dto.user.UpdateUserRequest;
import com.example.user_microservice.dto.user.UserResponse;
import com.example.user_microservice.mapper.UserMapper;
import com.example.user_microservice.model.PageResponse;
import com.example.user_microservice.model.user.UserModel;
import com.example.user_microservice.model.user.enums.Role;
import com.example.user_microservice.repository.KeycloakRepository;
import com.example.user_microservice.service.SearchRequest;
import com.example.user_microservice.service.UserService;
import com.example.user_microservice.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/users")
public class KeycloakAdminController {
    @Autowired
    private UserService service;
    @Autowired
    private KeycloakRepository keycloakRepository;
    @Autowired
    private LocationClient locationClient;

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @PostMapping
    public UserResponse createUser(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateUserRequest request
    ) {
        return service.createUser(request, token);
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @GetMapping("roles")
    public List<RoleMapping> getRealmRoles(
            @RequestHeader("Authorization") String token
    ){
        return keycloakRepository.getRealmRoles(token);
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @PatchMapping("/{userId}")
    public UserResponse updateUser(
            @PathVariable String userId,
            @RequestBody UpdateUserRequest request
    ) {
        return UserMapper.responseFromModel(
                service.updateUser(
                        userId,
                        request
                )
        );
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @GetMapping()
    public List<UserModel> getAll(){
        return service.getAllUsers();
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @DeleteMapping("/{userId}")
    public void deleteUser(
            @PathVariable String userId,
            @RequestHeader("Authorization") String token
    ) {
        service.deleteUser(userId, token);
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @GetMapping("/{userId}")
    public UserResponse getUser(
            @PathVariable String userId
    ) {
        return UserMapper.responseFromModel(service.getUserById(userId));
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @GetMapping("/detail/{userId}")
    public UserResponse getUserDetail(
            @PathVariable String userId
    ) {
        UserModel model = service.getUserById(userId);
        LocationResponse locationResponse = locationClient.getById(model.getLocationId());
        return UserMapper.responseFromModelDetail(model, locationResponse, service.canDelete(model));
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @GetMapping("/candelete/{userId}")
    public boolean getUserCanDelete(
            @PathVariable String userId
    ) {
        UserModel model = service.getUserById(userId);
        return service.canDelete(model);
    }

    @PreAuthorize("hasRole(@roleProperties.admin)")
    @PostMapping("/search")
    public PageResponse<UserResponse> search(
            @RequestBody SearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<UserModel> result = service.search(request, page, size);
        return pageToResponse(result);
    }

    public PageResponse<UserResponse> pageToResponse(PageResponse<UserModel> page){
        return PageResponse.<UserResponse>builder()
                .content(
                        page.getContent().stream()
                                .map(UserMapper::responseFromModel)
                                .toList()
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPage())
                .size(page.getSize())
                .build();
    }
}