package com.example.location_microservice.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        Set<GrantedAuthority> authorities = new HashSet<>();

        // REALM ROLES
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");

        if (realmAccess != null && realmAccess.get("roles") instanceof Collection<?> roles) {
            roles.forEach(role ->
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role))
            );
        }

        // CLIENT ROLES
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");

        if (resourceAccess != null) {
            resourceAccess.forEach((client, value) -> {

                if (value instanceof Map<?, ?> clientMap) {
                    Object clientRolesObj = clientMap.get("roles");

                    if (clientRolesObj instanceof Collection<?> clientRoles) {
                        clientRoles.forEach(role ->
                                authorities.add(new SimpleGrantedAuthority("ROLE_" + role))
                        );
                    }
                }
            });
        }

        return authorities;
    }
}