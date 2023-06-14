package com.spring.boot.security.saml.controllers;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.boot.security.saml.dto.RoleResponse;
import com.spring.boot.security.saml.dto.UserResponse;

/**
 * This is a feign client to get users and groups data from your own identity store service
 * you can change it as per your requirement.
 */
@FeignClient(value = "EXTENAL-IDENTITY-SERVICE", url = "https://localhost:8443/")
public interface ExternalIdentityServiceFeignClient {

    @RequestMapping(path = "/users/all", method = RequestMethod.GET)
    List<UserResponse> getUsers();

    @RequestMapping(path = "/roles/all", method = RequestMethod.GET)
    List<RoleResponse> getRoles();

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    UserResponse getUserById(@RequestHeader("username") String userId);

    @RequestMapping(path = "/roles/{role_name}/all_users", method = RequestMethod.GET)
    List<UserResponse> getUsersForRole(@PathVariable("role_name") String groupId);
}
