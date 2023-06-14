package com.spring.boot.security.saml.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String username;
    private String firstName;
    @JsonProperty("default_role")
    private String defaultRole;
    @JsonProperty("current_role")
    private String currentRole;
    private String country;
    private List<String> roles;
}
