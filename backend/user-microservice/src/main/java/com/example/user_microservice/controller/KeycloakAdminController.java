package com.example.user_microservice.controller;

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

    @PreAuthorize("hasRole('system-admin')")
    @PostMapping
    public UserResponse createUser(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateUserRequest request
    ) {
        return service.createUser(request, token);
    }

    @PreAuthorize("hasRole('system-admin')")
    @GetMapping("roles")
    public List<RoleMapping> getRealmRoles(
            @RequestHeader("Authorization") String token
    ){
        return keycloakRepository.getRealmRoles(token);
    }

    @PreAuthorize("hasRole('system-admin')")
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

    @PreAuthorize("hasRole('system-admin')")
    @GetMapping()
    public List<UserModel> getAll(){
        return service.getAllUsers();
    }

    @PreAuthorize("hasRole('system-admin')")
    @DeleteMapping("/{userId}")
    public void deleteUser(
            @PathVariable String userId
    ) {
        service.deleteUser(userId);
    }

    @PreAuthorize("hasRole('system-admin')")
    @GetMapping("/{userId}")
    public UserResponse getUser(
            @PathVariable String userId
    ) {
        return UserMapper.responseFromModel(service.getUserById(userId));
    }

    @PreAuthorize("hasRole('system-admin')")
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