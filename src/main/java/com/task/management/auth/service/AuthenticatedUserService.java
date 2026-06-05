package com.task.management.auth.service;

import com.task.management.auth.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserService {

    public User getAuthenticatedUser(){
        //returns the currently authenticated user (since they passed through the chain's filter)
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
