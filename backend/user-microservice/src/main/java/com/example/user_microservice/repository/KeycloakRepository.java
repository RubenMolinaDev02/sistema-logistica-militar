package com.example.user_microservice.repository;

import com.example.user_microservice.dto.role.RoleMapping;
import com.example.user_microservice.dto.user.CreateUserRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class KeycloakRepository {

    private final WebClient webClient = WebClient.create("http://localhost:9090");

    private static final String REALM = "armory-realm";

    // =========================
    // USER CRUD
    // =========================

    public String createUser(String token, CreateUserRequest request) {

        return webClient.post()
                .uri("/admin/realms/{realm}/users", REALM)
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of(
                        "username", request.getUsername(),
                        "email", request.getEmail(),
                        "enabled", true,
                        "emailVerified", true,
                        "firstName", request.getFirstName(),
                        "lastName", request.getLastName()
                ))
                .retrieve()
                .toBodilessEntity()
                .map(resp -> extractUserIdFromLocation(resp.getHeaders().getLocation()))
                .block();
    }

    public void updateUser(String token, String userId, String username, String email, boolean enabled) {

        webClient.put()
                .uri("/admin/realms/{realm}/users/{id}", REALM, userId)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of(
                        "username", username,
                        "email", email,
                        "enabled", enabled
                ))
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public void deleteUser(String token, String userId) {

        webClient.delete()
                .uri("/admin/realms/{realm}/users/{id}", REALM, userId)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public Map<String, Object> getUser(String token, String userId) {

        return webClient.get()
                .uri("/admin/realms/{realm}/users/{id}", REALM, userId)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    public List<Map> searchUsers(String token, String query) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/realms/{realm}/users")
                        .queryParam("search", query)
                        .build(REALM))
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }

    // =========================
    // PASSWORD
    // =========================

    public void updatePassword(String token, String userId, String newPassword) {

        webClient.put()
                .uri("/admin/realms/{realm}/users/{id}/reset-password", REALM, userId)
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of(
                        "type", "password",
                        "value", newPassword,
                        "temporary", false
                ))
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    // =========================
    // ROLES
    // =========================

    public void assignRealmRoles(String token, String userId, List<RoleMapping> roles) {

        webClient.post()
                .uri("/admin/realms/{realm}/users/{id}/role-mappings/realm", REALM, userId)
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(roles)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public void removeRealmRoles(String token, String userId, List<Map<String, Object>> roles) {

        webClient.method(org.springframework.http.HttpMethod.DELETE)
                .uri("/admin/realms/{realm}/users/{id}/role-mappings/realm", REALM, userId)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(roles)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public List<Map> getUserRoles(String token, String userId) {

        return webClient.get()
                .uri("/admin/realms/{realm}/users/{id}/role-mappings/realm", REALM, userId)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }

    // =========================
    // ROLE INFO
    // =========================

    public List<RoleMapping> getRealmRoles(String token) {

        return webClient.get()
                .uri("/admin/realms/{realm}/roles", REALM)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }

    public List<RoleMapping> getRoleByName(String token, String roleName) {

        RoleMapping role = webClient.get()
                .uri("/admin/realms/{realm}/roles/{role}", REALM, roleName)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(RoleMapping.class)
                .block();

        return role != null ? List.of(role) : List.of();
    }

    public boolean userExists(String token, String username) {

        List<Map> users = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/realms/{realm}/users")
                        .queryParam("username", username)
                        .build(REALM))
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(List.class)
                .block();

        return users != null && !users.isEmpty();
    }

    // =========================
    // UTIL
    // =========================

    private String extractUserIdFromLocation(java.net.URI location) {
        if (location == null) return null;
        String path = location.getPath();
        return path.substring(path.lastIndexOf('/') + 1);
    }
}