package com.spring.boot.security.saml.plugin;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.boot.security.saml.idservices.CustomIdentityProviderFactory;
import com.spring.boot.security.saml.services.GroupService;
import com.spring.boot.security.saml.services.UserService;

/**
 * This is the custom process engine plugin to replace Camunda's default identity service
 */
@Component
public class CustomIdentityProviderPlugin implements ProcessEnginePlugin {

    private final UserService userService;

    private final GroupService groupService;

    @Autowired
    public CustomIdentityProviderPlugin(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        CustomIdentityProviderFactory identityProviderFactory = new CustomIdentityProviderFactory(userService, groupService);
        processEngineConfiguration.setIdentityProviderSessionFactory(identityProviderFactory);
    }

    @Override
    public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

    }

    @Override
    public void postProcessEngineBuild(ProcessEngine processEngine) {

    }
}
