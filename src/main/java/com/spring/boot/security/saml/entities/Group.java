package com.spring.boot.security.saml.entities;

import lombok.Data;

@Data
public class Group implements org.camunda.bpm.engine.identity.Group {
    String id;
    String name;
    String type;
}
