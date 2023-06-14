package com.spring.boot.security.saml.idservices;

import org.camunda.bpm.engine.impl.identity.ReadOnlyIdentityProvider;
import org.camunda.bpm.engine.impl.interceptor.Session;
import org.camunda.bpm.engine.impl.interceptor.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.saml.services.GroupService;
import com.spring.boot.security.saml.services.UserService;

@Service
public class CustomIdentityProviderFactory implements SessionFactory {

    private final UserService userService;

    private final GroupService groupService;

    @Autowired
    public CustomIdentityProviderFactory(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    @Override
    public Class<?> getSessionType() {
        return ReadOnlyIdentityProvider.class;
    }

    @Override
    public Session openSession() {
        return new CustomIdentityProvider(userService, groupService);
    }
}
