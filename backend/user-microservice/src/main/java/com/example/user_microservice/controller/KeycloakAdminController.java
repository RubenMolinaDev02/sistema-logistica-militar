package com.example.user_microservice.controller;

import com.example.user_microservice.dto.role.RoleMapping;
import com.example.user_microservice.dto.user.CreateUserRequest;
import com.example.user_microservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/users")
public class KeycloakAdminController {
    @Autowired
    private UserService service;

    private String cleanToken(String token) {
        return token.replace("Bearer ", "");
    }

    @PostMapping
    public void createUser(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateUserRequest request
    ) {
        service.createUser(request, token, request.getRole());
    }
/*
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(
            @RequestHeader("Authorization") String token,
            @PathVariable String userId,
            @RequestBody CreateUserRequest request
    ) {

        service.updateUser(
                cleanToken(token),
                userId,
                request.getUsername(),
                request.getEmail(),
                true
        );

        return ResponseEntity.noContent().build();
    }

    // =========================
    // DELETE USER
    // =========================
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @RequestHeader("Authorization") String token,
            @PathVariable String userId
    ) {

        service.deleteUser(cleanToken(token), userId);

        return ResponseEntity.noContent().build();
    }

    // =========================
    // GET USER
    // =========================
    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getUser(
            @RequestHeader("Authorization") String token,
            @PathVariable String userId
    ) {

        return ResponseEntity.ok(
                service.getUser(cleanToken(token), userId)
        );
    }

    // =========================
    // SEARCH USERS
    // =========================
    @GetMapping
    public ResponseEntity<List<Map>> searchUsers(
            @RequestHeader("Authorization") String token,
            @RequestParam String query
    ) {

        return ResponseEntity.ok(
                service.searchUsers(cleanToken(token), query)
        );
    }

    // =========================
    // UPDATE PASSWORD
    // =========================
    @PutMapping("/{userId}/password")
    public ResponseEntity<Void> updatePassword(
            @RequestHeader("Authorization") String token,
            @PathVariable String userId,
            @RequestParam String password
    ) {

        service.updatePassword(cleanToken(token), userId, password);

        return ResponseEntity.noContent().build();
    }

    // =========================
    // ASSIGN ROLE
    // =========================
    @PostMapping("/{userId}/roles")
    public ResponseEntity<Void> assignRole(
            @RequestHeader("Authorization") String token,
            @PathVariable String userId,
            @RequestBody List<RoleMapping> requests
    ) {

        service.assignRealmRoles(
                cleanToken(token),
                userId,
                requests
        );

        return ResponseEntity.noContent().build();
    }

    // =========================
    // REMOVE ROLE
    // =========================
    @DeleteMapping("/{userId}/roles")
    public ResponseEntity<Void> removeRole(
            @RequestHeader("Authorization") String token,
            @PathVariable String userId,
            @RequestBody RoleMapping request
    ) {

        service.removeRealmRoles(
                cleanToken(token),
                userId,
                List.of(Map.of(
                        "id", request.getId(),
                        "name", request.getName()
                ))
        );

        return ResponseEntity.noContent().build();
    }

    // =========================
    // GET USER ROLES
    // =========================
    @GetMapping("/{userId}/roles")
    public ResponseEntity<List<Map>> getUserRoles(
            @RequestHeader("Authorization") String token,
            @PathVariable String userId
    ) {

        return ResponseEntity.ok(
                service.getUserRoles(cleanToken(token), userId)
        );
    }
    */
}