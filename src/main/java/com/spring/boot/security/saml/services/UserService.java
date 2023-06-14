package com.spring.boot.security.saml.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.saml.controllers.ExternalIdentityServiceFeignClient;
import com.spring.boot.security.saml.dto.UserResponse;
import com.spring.boot.security.saml.entities.User;

@Service
public class UserService {

    @Autowired
    ExternalIdentityServiceFeignClient externalIdentityServiceFeignClient;

    public User findById(String id) {
        return toUser(externalIdentityServiceFeignClient.getUserById(id));
    }

    public Collection<User> findAll() {
        List<UserResponse> userResponseList = externalIdentityServiceFeignClient.getUsers();
        return userResponseList.stream().map(userResponse ->toUser(userResponse)).collect(Collectors.toList());
    }


    private User toUser(UserResponse userResponse){
        User user = new User();
        user.setId(userResponse.getUsername());
        user.setEmail(userResponse.getUsername());
        user.setFirstName(userResponse.getFirstName());
        user.setLastName("");
        return user;
    }

    public Collection<User> findByGroupId(String groupId){
        return externalIdentityServiceFeignClient
                .getUsersForRole(groupId).stream().map(userResponse -> toUser(userResponse))
                .collect(Collectors.toList());
    }

}
