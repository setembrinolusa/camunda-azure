package com.spring.boot.security.saml.entities;

import java.util.List;

import lombok.Data;

@Data
public class User implements org.camunda.bpm.engine.identity.User {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Group> group;
}
